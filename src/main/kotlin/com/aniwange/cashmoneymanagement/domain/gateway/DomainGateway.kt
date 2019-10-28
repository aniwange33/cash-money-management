package com.aniwange.cashmoneymanagement.domain.gateway

import com.aniwange.cashmoneymanagement.domain.CustomerDomain
import com.aniwange.cashmoneymanagement.domain.TransactionDomain
import com.aniwange.cashmoneymanagement.domain.model.CustomerRegistrationCommand
import com.aniwange.cashmoneymanagement.domain.model.TransactionRequestCommand


interface  CustomerGateway{
    fun registerACustomer(customerRegistrationCommand: CustomerRegistrationCommand): CustomerDomain
    fun fetchAllCustomers(): List<CustomerDomain>
    fun searchACustomerByNameOrPhone(searchTerm: String): List<CustomerDomain>
    fun countCustomer(): Int

}


interface  TransactionGateway{
  fun performCustomerTransaction(transactionRequestCommand: TransactionRequestCommand): TransactionDomain
    fun fetchAllCustomerTransaction(): List<TransactionDomain>
    fun getTotalTransactionAmount(): Double
    fun getTotalTransactionCharges(): Double
}