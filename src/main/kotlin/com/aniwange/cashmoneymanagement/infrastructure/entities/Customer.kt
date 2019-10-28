package com.aniwange.cashmoneymanagement.infrastructure.entities

import javax.persistence.Entity
import javax.persistence.Table


@Entity
@Table(name="customer")
class Customer(val surname: String): BaseEntity<Long>() {
    var firstName: String =""
    var lastName: String = ""
    var email : String = ""
    var phoneNumber: String = ""
}