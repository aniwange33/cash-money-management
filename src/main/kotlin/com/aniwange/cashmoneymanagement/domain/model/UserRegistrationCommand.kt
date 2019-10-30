package com.aniwange.cashmoneymanagement.domain.model

import com.aniwange.cashmoneymanagement.domain.model.enumeration.Role

class UserRegistrationCommand(val fullName: String, val email: String, val phone: String) {
    var password: String = ""
    lateinit var role: Role
}
