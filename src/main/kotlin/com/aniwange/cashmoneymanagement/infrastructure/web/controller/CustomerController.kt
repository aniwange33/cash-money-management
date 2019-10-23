package com.aniwange.cashmoneymanagement.infrastructure.web.controller

import com.aniwange.cashmoneymanagement.domain.CustomerDomain
import com.aniwange.cashmoneymanagement.domain.model.CustomerRegistrationCommand
import com.aniwange.cashmoneymanagement.domain.model.CustomerRegistrationRespondCommand
import com.aniwange.cashmoneymanagement.usecases.FetchAllCustomers
import com.aniwange.cashmoneymanagement.usecases.RegisterACustomer
import com.aniwange.cashmoneymanagement.usecases.SearchACustomerByNameOrPhone
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class CustomerController(val registerACustomer: RegisterACustomer, val fetchAllCustomers: FetchAllCustomers, val searchACustomerByNameOrPhone: SearchACustomerByNameOrPhone) {

    @RequestMapping(value = "/customer", method = [RequestMethod.POST])
    fun registerACustomer(@RequestBody customerRegistrationCommand: CustomerRegistrationCommand):ResponseEntity<CustomerRegistrationRespondCommand>{
        return ResponseEntity.ok(registerACustomer.registerACustomer(customerRegistrationCommand))
    }

    @RequestMapping(value = "/customer/search/{term}", method = [RequestMethod.GET])
    fun searchACustomerByNameOrPhone(@PathVariable("term") term: String):ResponseEntity<List<CustomerDomain>>{
        val searchTerm  = if(term.isName(term)) term.toLowerCase() else term
        println("search term: $searchTerm")
        return ResponseEntity.ok(searchACustomerByNameOrPhone.searchCustomerByNameOrPhone(searchTerm))
    }

    @RequestMapping(value = ["/customer/list"], method = [RequestMethod.GET])
    fun listAllCustomers():ResponseEntity<List<CustomerDomain>>{
        return ResponseEntity.ok(fetchAllCustomers.fetchAllCustomers())
    }
}

private fun String.isName(term: String): Boolean {
    return (term != ""
            && term != null
            && term.matches("^[a-zA-Z]*$".toRegex()))



}