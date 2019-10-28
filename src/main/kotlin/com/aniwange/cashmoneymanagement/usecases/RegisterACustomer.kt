package com.aniwange.cashmoneymanagement.usecases

import com.aniwange.cashmoneymanagement.domain.CustomerDomain
import com.aniwange.cashmoneymanagement.domain.model.CustomerRegistrationCommand

interface RegisterACustomer {
    fun registerACustomer(customerRegistrationCommand: CustomerRegistrationCommand): CustomerDomain
}