package com.aniwange.cashmoneymanagement.usecases.impl

import com.aniwange.cashmoneymanagement.domain.gateway.CustomerGateway
import com.aniwange.cashmoneymanagement.usecases.SearchACustomerByNameOrPhone
import org.springframework.stereotype.Service

@Service
class SearchACustomerByNameOrPhoneImpl(private val customerGateway: CustomerGateway) : SearchACustomerByNameOrPhone {
    override fun searchCustomerByNameOrPhone(searchTerm: String) = customerGateway.searchACustomerByNameOrPhone(searchTerm)

}