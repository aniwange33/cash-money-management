package com.aniwange.cashmoneymanagement.usecases

interface ReportTransactionAmountAndCharges {
    fun getTotalTransactionAmount(): Double
    fun getTotalTransactionCharges(): Double
}