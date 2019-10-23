package com.aniwange.cashmoneymanagement.infrastructure.web.controller

import com.aniwange.cashmoneymanagement.infrastructure.security.jwt.TokenService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {
    @Autowired
    lateinit var tokenService: TokenService
    var attributes = mutableMapOf("102" to "Admin")
    @GetMapping("/hello")
    fun greetSomeOne() = testingChecking(false) + tokenService.permanentToken(attributes)
}
fun testingChecking(isAvailable: Boolean): String{
    return "hello  "+ if(isAvailable) "Amos: " else "Jerry: "

}
