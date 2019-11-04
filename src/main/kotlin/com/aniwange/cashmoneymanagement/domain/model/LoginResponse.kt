package com.aniwange.cashmoneymanagement.domain.model

import com.aniwange.cashmoneymanagement.domain.UserDomain

data class LoginResponse(val userDomain: UserDomain, var token: String) {

}
