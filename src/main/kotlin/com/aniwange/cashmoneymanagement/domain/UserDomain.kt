package com.aniwange.cashmoneymanagement.domain

import com.aniwange.cashmoneymanagement.domain.model.enumeration.Role

class UserDomain(val name: String,  val password: String, val email: String) {
   lateinit var role: Role
    var id : Long = 0
    var phone: String = ""
}