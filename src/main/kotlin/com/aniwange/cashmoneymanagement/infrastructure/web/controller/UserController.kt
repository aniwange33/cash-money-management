package com.aniwange.cashmoneymanagement.infrastructure.web.controller

import com.aniwange.cashmoneymanagement.domain.UserDomain
import com.aniwange.cashmoneymanagement.domain.model.LoginRequestCommand
import com.aniwange.cashmoneymanagement.domain.model.LoginResponse
import com.aniwange.cashmoneymanagement.domain.model.UserRegistrationCommand
import com.aniwange.cashmoneymanagement.usecases.LoginUser
import com.aniwange.cashmoneymanagement.usecases.RegisterAUser
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/api/app/auth/")
class UserController(val registerAUser: RegisterAUser, val loginUser: LoginUser) {
    @PostMapping("user")
    fun registerAUser(@RequestBody userRegistrationCommand: UserRegistrationCommand): ResponseEntity<UserDomain>{
        return ResponseEntity.ok(registerAUser.registerAUser(userRegistrationCommand))
    }

    @PostMapping("user/login")
    fun login(@RequestBody loginRequestCommand: LoginRequestCommand): ResponseEntity<LoginResponse>{
        return ResponseEntity.ok(loginUser.loginUser(loginRequestCommand))
    }

}






//    @Autowired
//    lateinit var tokenService: TokenService
//    var attributes = mutableMapOf("102" to "Admin")
//    @GetMapping("/hello")
//    fun greetSomeOne() = testingChecking(false) + tokenService.permanentToken(attributes)