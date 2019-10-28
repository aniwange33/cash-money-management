package com.aniwange.cashmoneymanagement.infrastructure.entities

import com.aniwange.cashmoneymanagement.domain.model.enumeration.TransactionType
import javax.persistence.*

@Entity
@Table(name = "transaction")
class Transaction(val amount: Double, val charge: Double): BaseEntity<Long>() {
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    lateinit var  customer: Customer
    @Enumerated(EnumType.STRING)
    lateinit var  transactionType: TransactionType

}