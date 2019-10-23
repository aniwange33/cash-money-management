package com.aniwange.cashmoneymanagement.usecases

import com.aniwange.cashmoneymanagement.domain.CustomerDomain

interface FetchAllCustomers {
    fun fetchAllCustomers(): List<CustomerDomain>
}