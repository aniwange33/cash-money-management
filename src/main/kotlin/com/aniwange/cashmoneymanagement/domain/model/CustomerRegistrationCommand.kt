package com.aniwange.cashmoneymanagement.domain.model

 data class CustomerRegistrationCommand(val phone: String, val email:String?) {
    lateinit var name : Name

}

data class  Name(val surname: String, var middleName: String?, var lastName: String?)