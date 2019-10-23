package com.aniwange.cashmoneymanagement.usecases.impl

import com.aniwange.cashmoneymanagement.domain.gateway.CustomerGateway
import com.aniwange.cashmoneymanagement.domain.model.CustomerRegistrationCommand
import com.aniwange.cashmoneymanagement.domain.model.CustomerRegistrationRespondCommand
import com.aniwange.cashmoneymanagement.usecases.RegisterACustomer
import org.springframework.stereotype.Service

@Service
class RegisterACustomerImpl(private val customerGateway: CustomerGateway) : RegisterACustomer {
    override fun registerACustomer(customerRegistrationCommand: CustomerRegistrationCommand): CustomerRegistrationRespondCommand {
        return  customerGateway.registerACustomer(customerRegistrationCommand)
    }
}