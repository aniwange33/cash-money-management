package com.aniwange.cashmoneymanagement.domain

import com.aniwange.cashmoneymanagement.domain.model.enumeration.TransactionType
import org.joda.time.DateTime

data class TransactionDomain(val id:Long, val  amount: Double, val charge: Double) {
    lateinit var customerDomain: CustomerDomain
    lateinit var date : DateTime
    lateinit var  transactionType: TransactionType

}