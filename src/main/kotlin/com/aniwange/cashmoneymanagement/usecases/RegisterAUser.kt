package com.aniwange.cashmoneymanagement.usecases

import com.aniwange.cashmoneymanagement.domain.UserDomain
import com.aniwange.cashmoneymanagement.domain.model.UserRegistrationCommand

interface RegisterAUser {
    fun registerAUser(userRegistrationCommand: UserRegistrationCommand): UserDomain
}