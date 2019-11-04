package com.aniwange.cashmoneymanagement.domain

import com.aniwange.cashmoneymanagement.domain.model.enumeration.Role
import com.fasterxml.jackson.annotation.JsonIgnore

class UserDomain(val name: String,
                 @JsonIgnore
                 var password: String,
                 val email: String) {
   lateinit var role: Role
    var id : Long = 0
    var phone: String = ""
}