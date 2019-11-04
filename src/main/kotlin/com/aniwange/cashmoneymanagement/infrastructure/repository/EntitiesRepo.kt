package com.aniwange.cashmoneymanagement.infrastructure.repository

import com.aniwange.cashmoneymanagement.infrastructure.entities.Customer
import com.aniwange.cashmoneymanagement.infrastructure.entities.Transaction
import com.aniwange.cashmoneymanagement.infrastructure.entities.User
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*


@Repository
interface  UserRepository: JpaRepository<User, Long>{
    fun getUserByEmail(email: String): User
    fun findUserByEmail(email: String): Optional<User>
}

@Repository
interface  TransactionRepository: JpaRepository<Transaction, Long> {
    @Query("select  sum (t.amount) from  Transaction  t ")
    fun getTotalAmount(): Double

    @Query("select  sum (t .charge) from  Transaction t")
    fun getTotalCharge(): Double
}

@Repository
interface  CustomerRepository: JpaRepository<Customer, Long> {
    @Query("select c from Customer c where  lower(c.surname) like :term% or c.phoneNumber like :term%")
    fun searchCustomerByNameOrPhoneNumber(@Param("term") term: String, pageable: Pageable): List<Customer>
}



