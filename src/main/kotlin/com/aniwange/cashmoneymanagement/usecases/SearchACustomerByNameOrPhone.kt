package com.aniwange.cashmoneymanagement.usecases

import com.aniwange.cashmoneymanagement.domain.CustomerDomain


interface SearchACustomerByNameOrPhone {
    fun searchCustomerByNameOrPhone(searchTerm: String): List<CustomerDomain>
}