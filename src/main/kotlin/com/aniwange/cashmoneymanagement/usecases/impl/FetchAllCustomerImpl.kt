package com.aniwange.cashmoneymanagement.usecases.impl

import com.aniwange.cashmoneymanagement.domain.gateway.CustomerGateway
import com.aniwange.cashmoneymanagement.usecases.FetchAllCustomers
import org.springframework.stereotype.Service

@Service
class FetchAllCustomerImpl(private val customerGateway: CustomerGateway)  : FetchAllCustomers{
    override fun fetchAllCustomers() = customerGateway.fetchAllCustomers()
}