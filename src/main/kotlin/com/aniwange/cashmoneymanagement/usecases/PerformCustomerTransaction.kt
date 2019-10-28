package com.aniwange.cashmoneymanagement.usecases

import com.aniwange.cashmoneymanagement.domain.TransactionDomain
import com.aniwange.cashmoneymanagement.domain.model.TransactionRequestCommand

interface PerformCustomerTransaction {
    fun processCustomerTransaction(transactionRequestCommand: TransactionRequestCommand): TransactionDomain
    fun fetchAllTransaction(): List<TransactionDomain>
}