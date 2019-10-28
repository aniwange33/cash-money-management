package com.aniwange.cashmoneymanagement.usecases.impl

import com.aniwange.cashmoneymanagement.domain.gateway.TransactionGateway
import com.aniwange.cashmoneymanagement.usecases.ReportTransactionAmountAndCharges
import org.springframework.stereotype.Service

@Service
class ReportTransactionAmountAndChargesImpl(private val transactionGateway : TransactionGateway): ReportTransactionAmountAndCharges {

    override fun getTotalTransactionAmount() =  transactionGateway.getTotalTransactionAmount()

    override fun getTotalTransactionCharges() = transactionGateway.getTotalTransactionCharges()

}