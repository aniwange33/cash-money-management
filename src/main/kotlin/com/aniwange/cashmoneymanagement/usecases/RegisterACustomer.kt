package com.aniwange.cashmoneymanagement.usecases

import com.aniwange.cashmoneymanagement.domain.model.CustomerRegistrationCommand
import com.aniwange.cashmoneymanagement.domain.model.CustomerRegistrationRespondCommand

interface RegisterACustomer {
    fun registerACustomer(customerRegistrationCommand: CustomerRegistrationCommand): CustomerRegistrationRespondCommand
}