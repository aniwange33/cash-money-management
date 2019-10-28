package com.aniwange.cashmoneymanagement.infrastructure.entities


import java.sql.Timestamp
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class BaseEntity<T> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: T? = null

    private var dateCreated: Timestamp = Timestamp(System.currentTimeMillis())

}