package com.aniwange.cashmoneymanagement.domain.model

data class CustomerRegistrationRespondCommand(val id: Long, val name: Name) {
     var phone: String? = null
     var  email: String? = null
}