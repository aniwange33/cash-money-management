package com.aniwange.cashmoneymanagement.infrastructure.security

import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class AuthenticatedUser : UserDetails {

    private var authorities: MutableList<SimpleGrantedAuthority> = ArrayList()
    var email: String = ""
    var userPassword: String = ""
    var userId : Long = 0
    var blocked: Boolean = false
    private var expiredCredential: Boolean = false

    fun addAuthority(grantedAuthority: SimpleGrantedAuthority) {
        authorities.add(grantedAuthority)
    }

    override fun getAuthorities(): List<SimpleGrantedAuthority> = authorities

    override fun isEnabled(): Boolean = !blocked

    override fun getUsername(): String = email

    override fun isCredentialsNonExpired(): Boolean = !expiredCredential

    override fun getPassword(): String = userPassword

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = !blocked
}
