package com.aniwange.cashmoneymanagement.domain

import com.aniwange.cashmoneymanagement.domain.model.enumeration.TransactionType
import java.sql.Timestamp

data class TransactionDomain(val id:Long, val  amount: Double, val charge: Double) {
    lateinit var customerDomain: CustomerDomain
    var date = Timestamp(System.currentTimeMillis())
    lateinit var  transactionType: TransactionType
}