package com.aniwange.cashmoneymanagement.domain.gateway

import com.aniwange.cashmoneymanagement.domain.CustomerDomain
import com.aniwange.cashmoneymanagement.domain.TransactionDomain
import com.aniwange.cashmoneymanagement.domain.UserDomain
import com.aniwange.cashmoneymanagement.domain.model.CustomerRegistrationCommand
import com.aniwange.cashmoneymanagement.domain.model.TransactionRequestCommand
import com.aniwange.cashmoneymanagement.domain.model.UserRegistrationCommand


interface  CustomerGateway{
    fun registerACustomer(customerRegistrationCommand: CustomerRegistrationCommand): CustomerDomain
    fun fetchAllCustomers(): List<CustomerDomain>
    fun searchACustomerByNameOrPhone(searchTerm: String): List<CustomerDomain>
    fun countCustomer(): Long

}


interface  TransactionGateway{
  fun performCustomerTransaction(transactionRequestCommand: TransactionRequestCommand): TransactionDomain
    fun fetchAllCustomerTransaction(): List<TransactionDomain>
    fun getTotalTransactionAmount(): Double
    fun getTotalTransactionCharges(): Double
}

interface  UserGateway{
    fun registerAUser(userRegistrationCommand: UserRegistrationCommand): UserDomain
    fun findById(id: Long): UserDomain

}