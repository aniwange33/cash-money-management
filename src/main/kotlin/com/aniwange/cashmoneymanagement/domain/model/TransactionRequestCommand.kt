package com.aniwange.cashmoneymanagement.domain.model

import com.aniwange.cashmoneymanagement.domain.model.enumeration.ChargeType
import com.aniwange.cashmoneymanagement.domain.model.enumeration.TransactionType


data class TransactionRequestCommand(val customerId: Long, val amount: Double) {
    lateinit var  transactionType: TransactionType
    lateinit var  chargeType : ChargeType
    var beneficialAccountNumber: String? = null
    var senderName: String? = null

}
