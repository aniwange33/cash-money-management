package com.aniwange.cashmoneymanagement.infrastructure.entities

import com.aniwange.cashmoneymanagement.domain.model.enumeration.Role
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Table



@Entity
@Table(name = "app_user")
class User(val name: String,
           val phone: String , val email: String): BaseEntity<Long>() {
    var password: String = ""
    @Enumerated(EnumType.STRING)
    lateinit var role: Role
}