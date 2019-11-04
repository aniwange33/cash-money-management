package com.aniwange.cashmoneymanagement.usecases.impl

import com.aniwange.cashmoneymanagement.domain.gateway.UserGateway
import com.aniwange.cashmoneymanagement.domain.model.LoginRequestCommand
import com.aniwange.cashmoneymanagement.domain.model.LoginResponse
import com.aniwange.cashmoneymanagement.infrastructure.security.jwt.TokenService
import com.aniwange.cashmoneymanagement.usecases.LoginUser
import javassist.NotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class LoginUserImpl(private val userGateway: UserGateway): LoginUser {



    @Autowired
    lateinit var tokenService: TokenService

    override fun loginUser(loginRequestCommand: LoginRequestCommand): LoginResponse {
        val appUser = userGateway.findByEmail(loginRequestCommand.email)
        println("1: "+appUser.password)
        println("2 "+passwordEncoder().encode(loginRequestCommand.password))
        if(!(passwordEncoder().matches(loginRequestCommand.password, appUser.password))){
          throw NotFoundException("wrong password")
        }
        val attribute = mapOf<String,String>("id" to appUser.id.toString(), "role" to appUser.role.name)
        val token = tokenService.permanentToken(attribute)
        return LoginResponse(appUser,token)

    }

    fun   passwordEncoder(): PasswordEncoder {
        return  BCryptPasswordEncoder()
    }
}