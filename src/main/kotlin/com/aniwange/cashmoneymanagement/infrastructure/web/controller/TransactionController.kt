package com.aniwange.cashmoneymanagement.infrastructure.web.controller

import com.aniwange.cashmoneymanagement.domain.TransactionDomain
import com.aniwange.cashmoneymanagement.domain.model.TransactionRequestCommand
import com.aniwange.cashmoneymanagement.usecases.PerformCustomerTransaction
import com.aniwange.cashmoneymanagement.usecases.ReportTransactionAmountAndCharges
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/")
class TransactionController(val performCustomerTransaction: PerformCustomerTransaction, val reportTransactionAmountAndCharges: ReportTransactionAmountAndCharges) {

    @RequestMapping(value = "customer/transaction", method = [RequestMethod.POST])
    fun registerCustomerTransaction(@RequestBody transactionRequestCommand: TransactionRequestCommand): ResponseEntity<TransactionDomain> {
        return ResponseEntity.ok(performCustomerTransaction.processCustomerTransaction(transactionRequestCommand))
    }

    @RequestMapping(value = "customer/transactions", method = [RequestMethod.GET])
    fun fetchAllRegisteredTransaction(): ResponseEntity<List<TransactionDomain>> {
        return ResponseEntity.ok(performCustomerTransaction.fetchAllTransaction())
    }

    @RequestMapping(value = "customer/transaction/amount", method = [RequestMethod.GET])
    fun getTotalTransactionAmount(): ResponseEntity<Double> {
        return ResponseEntity.ok(reportTransactionAmountAndCharges.getTotalTransactionAmount())
    }

    @RequestMapping(value = "customer/transaction/charges", method = [RequestMethod.GET])
    fun getTotalTransactionCharges(): ResponseEntity<Double> {
        return ResponseEntity.ok(reportTransactionAmountAndCharges.getTotalTransactionCharges())
    }
}