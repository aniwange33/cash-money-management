
package com.aniwange.cashmoneymanagement.infrastructure.security

import com.aniwange.cashmoneymanagement.domain.UserDomain
import com.aniwange.cashmoneymanagement.domain.gateway.UserGateway
import com.aniwange.cashmoneymanagement.infrastructure.security.jwt.TokenService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class TokenAuthenticationProvider : AbstractUserDetailsAuthenticationProvider() {

    @Autowired
    lateinit var userGateway: UserGateway

    @Autowired
    lateinit var tokenService: TokenService


    override fun retrieveUser(p0: String?, p1: UsernamePasswordAuthenticationToken?): UserDetails {
        val token = p1?.credentials as String?
        return token?.let { findByToken(it)} ?: throw UsernameNotFoundException("Invalid authorization token.")
    }

    override fun additionalAuthenticationChecks(p0: UserDetails?, p1: UsernamePasswordAuthenticationToken?) {
       logger.info("additionalAuthenticationChecks called")
    }

    fun fromAppUserToAuthenticatedUser(appUser: UserDomain): AuthenticatedUser {
        val authUser = AuthenticatedUser()
        authUser.email = appUser.email
        authUser.userId = appUser.id
        authUser.userPassword = appUser.password
        authUser.addAuthority(SimpleGrantedAuthority(appUser.role.name))
        return authUser
    }

    fun findByToken(token: String): AuthenticatedUser? {
        val attributes = tokenService.verify(token)
        if(attributes.isEmpty()) {
            println("token is empty")
            return null
        }
        val id: String = attributes["id"] ?: return null
        val userDomain = userGateway.findById(id as Long)
        return fromAppUserToAuthenticatedUser(userDomain)
    }
}
