package com.aniwange.cashmoneymanagement.usecases.impl

import com.aniwange.cashmoneymanagement.domain.TransactionDomain
import com.aniwange.cashmoneymanagement.domain.gateway.TransactionGateway
import com.aniwange.cashmoneymanagement.domain.model.TransactionRequestCommand
import com.aniwange.cashmoneymanagement.usecases.PerformCustomerTransaction
import org.springframework.stereotype.Service

@Service
class PerformCustomerTransactionImpl(private val transactionGateway: TransactionGateway): PerformCustomerTransaction {
    override fun processCustomerTransaction(transactionRequestCommand: TransactionRequestCommand): TransactionDomain {
        return  transactionGateway.performCustomerTransaction(transactionRequestCommand);
    }

    override fun fetchAllTransaction(): List<TransactionDomain> {
        return  transactionGateway.fetchAllCustomerTransaction();
    }
}