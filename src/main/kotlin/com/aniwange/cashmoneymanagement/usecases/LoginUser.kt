package com.aniwange.cashmoneymanagement.usecases

import com.aniwange.cashmoneymanagement.domain.model.LoginRequestCommand
import com.aniwange.cashmoneymanagement.domain.model.LoginResponse

interface  LoginUser {
    fun loginUser(loginRequestCommand: LoginRequestCommand): LoginResponse
}