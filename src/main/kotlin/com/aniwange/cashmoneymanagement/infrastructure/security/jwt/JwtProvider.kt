package com.aniwange.cashmoneymanagement.infrastructure.security.jwt

import io.jsonwebtoken.Clock
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.impl.compression.GzipCompressionCodec
import org.joda.time.DateTime
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*


interface TokenService{
    fun permanentToken(attributes: Map<String, String>): String
    fun expiryToken(attributes: Map<String, String>, expiryTimeInMinutes: Int): String
    fun verify(token : String): Map<String, String>
}

@Component
class  TokenServiceImpl: TokenService, Clock {


    @Value("\${app.token-issuer}")
    private lateinit var issuer: String

    @Value("\${app.token-secret}")
    private lateinit var secret: String

    private val clockSkewSec: Long = 60 * 3

    private val compressionCodec = GzipCompressionCodec()

    override fun permanentToken(attributes: Map<String, String>) = generateToken(attributes, 0)

    override fun expiryToken(attributes: Map<String, String>, expiryTimeInMinutes: Int) = generateToken(attributes, expiryTimeInMinutes)

    override fun verify(token: String): Map<String, String> {
        return try{
            val clams = Jwts.parser()
                    .setSigningKey(secret)
                    .setClock(this)
                    .setAllowedClockSkewSeconds(clockSkewSec)
                    .parseClaimsJws(token).body
            var  attributes = mutableMapOf<String, String>()
            clams.entries.forEach { attributes[it.key] = it.value.toString() }
            attributes
        }catch (e: JwtException){
            mapOf()
        }
    }

    override fun now(): Date = DateTime.now().toDate()


    private fun generateToken(attributes: Map<String, String>, expiryTimeInMinutes: Int): String {
        val now = DateTime.now()
        val claims = Jwts.claims().setIssuer(issuer).setIssuedAt(now.toDate())
        if(expiryTimeInMinutes > 0) claims.expiration = now.plusMinutes(expiryTimeInMinutes).toDate()
        claims.putAll(attributes)
        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256, secret)
                .compressWith(compressionCodec)
                .compact()
    }

}
