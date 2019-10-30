package com.aniwange.cashmoneymanagement.usecases.impl

import com.aniwange.cashmoneymanagement.domain.UserDomain
import com.aniwange.cashmoneymanagement.domain.gateway.UserGateway
import com.aniwange.cashmoneymanagement.domain.model.UserRegistrationCommand
import com.aniwange.cashmoneymanagement.usecases.RegisterAUser
import org.springframework.stereotype.Service

@Service
class RegisterAUserImpl(private val userGateway: UserGateway) : RegisterAUser {
    override fun registerAUser(userRegistrationCommand: UserRegistrationCommand): UserDomain {
        return userGateway.registerAUser(userRegistrationCommand);
    }
}