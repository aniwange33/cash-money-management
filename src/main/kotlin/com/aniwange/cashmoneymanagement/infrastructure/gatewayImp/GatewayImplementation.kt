package com.aniwange.cashmoneymanagement.infrastructure.gatewayImp

import com.aniwange.cashmoneymanagement.domain.CustomerDomain
import com.aniwange.cashmoneymanagement.domain.TransactionDomain
import com.aniwange.cashmoneymanagement.domain.gateway.CustomerGateway
import com.aniwange.cashmoneymanagement.domain.gateway.TransactionGateway
import com.aniwange.cashmoneymanagement.domain.model.CustomerRegistrationCommand
import com.aniwange.cashmoneymanagement.domain.model.TransactionRequestCommand
import com.aniwange.cashmoneymanagement.infrastructure.entities.Customer
import com.aniwange.cashmoneymanagement.infrastructure.entities.Transaction
import com.aniwange.cashmoneymanagement.infrastructure.repository.CustomerRepository
import com.aniwange.cashmoneymanagement.infrastructure.repository.TransactionRepository
import javassist.NotFoundException
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service


fun convertCustomerToCustomerDomain(customer: Customer): CustomerDomain{
    val id= customer.id ?: 0
    val phone =  customer.phoneNumber
    val customerDomain = CustomerDomain(id, phone)
    customerDomain.email = customer.email
    customerDomain.name = "${customer.surname}  ${customer.firstName}"
    return  customerDomain
}

fun convertTransactionToTransactionDomain(transaction: Transaction): TransactionDomain{
    val id = transaction.id ?: 0
    val amount  = transaction.amount
    val charges = transaction.charge
    val transactionDomain = TransactionDomain(id, amount,charges)
    transactionDomain.transactionType = transaction.transactionType
    transactionDomain.customerDomain = convertCustomerToCustomerDomain(transaction.customer)
    return  transactionDomain

}
@Service
class CustomerDomainGatewayImpl(val customerRepository: CustomerRepository): CustomerGateway {
    override fun registerACustomer(customerRegistrationCommand: CustomerRegistrationCommand): CustomerDomain {
        val customer = Customer(customerRegistrationCommand.name.surname)
             customer.firstName = customerRegistrationCommand.name.middleName ?: ""
             customer .lastName =  customerRegistrationCommand.name.lastName ?: ""
             customer.email = customerRegistrationCommand.email ?: ""
             customer.phoneNumber = customerRegistrationCommand.phone
        customerRepository.save(customer)
        return  convertCustomerToCustomerDomain(customer)
    }

    override fun fetchAllCustomers(): List<CustomerDomain> {
        val customers = customerRepository.findAll()
        return customers.map { convertCustomerToCustomerDomain(it) }
     }

    override fun countCustomer(): Int {
       return  customerRepository.count() as Int
    }

    override fun searchACustomerByNameOrPhone(searchTerm: String): List<CustomerDomain> {
        val pageable = PageRequest.of(0, 10)
        val customers = customerRepository.searchCustomerByNameOrPhoneNumber(searchTerm, pageable)
        return customers.map { convertCustomerToCustomerDomain(it) }
    }


}


@Service
class  TransactionDomainGatewayImpl(val transactionRepository: TransactionRepository, val customerRepository: CustomerRepository): TransactionGateway {
    override fun getTotalTransactionAmount() = transactionRepository.getTotalAmount()

    override fun getTotalTransactionCharges() = transactionRepository.getTotalCharge()

    override fun performCustomerTransaction(transactionRequestCommand: TransactionRequestCommand): TransactionDomain {
        val customerOptional = customerRepository.findById(transactionRequestCommand.customerId)
        if(!customerOptional.isPresent){
            throw NotFoundException("Customer not found!!")
        }
        val customer = customerOptional.get()
        val transaction = Transaction(transactionRequestCommand.amount, transactionRequestCommand.chargeType.calculateCharge())
        transaction.customer = customer
        transaction.transactionType = transactionRequestCommand.transactionType
        transactionRepository.save(transaction)
        return convertTransactionToTransactionDomain(transaction)
    }
    override fun fetchAllCustomerTransaction(): List<TransactionDomain> {
        val transactions = transactionRepository.findAll()
       return  transactions.map { convertTransactionToTransactionDomain(it) }
    }



}


