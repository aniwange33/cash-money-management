package com.aniwange.cashmoneymanagement.domain

data class CustomerDomain(val id:Long, val phone: String) {
    var  name: String = ""
    var  email: String? = null
}
