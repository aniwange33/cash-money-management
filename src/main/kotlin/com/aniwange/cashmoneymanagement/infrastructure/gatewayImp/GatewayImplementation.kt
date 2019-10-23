package com.aniwange.cashmoneymanagement.infrastructure.gatewayImp

import com.aniwange.cashmoneymanagement.domain.CustomerDomain
import com.aniwange.cashmoneymanagement.domain.TransactionDomain
import com.aniwange.cashmoneymanagement.domain.gateway.CustomerGateway
import com.aniwange.cashmoneymanagement.domain.gateway.TransactionGateway
import com.aniwange.cashmoneymanagement.domain.model.CustomerRegistrationCommand
import com.aniwange.cashmoneymanagement.domain.model.CustomerRegistrationRespondCommand
import com.aniwange.cashmoneymanagement.domain.model.TransactionRequestCommand
import org.springframework.stereotype.Service

@Service
class CustomerDomainGatewayImpl: CustomerGateway {
    override fun registerACustomer(customerRegistrationCommand: CustomerRegistrationCommand): CustomerRegistrationRespondCommand {
        val customerRegistrationRespondCommand = CustomerRegistrationRespondCommand(1, customerRegistrationCommand.name)
        customerRegistrationRespondCommand.email = customerRegistrationCommand.email
        customerRegistrationRespondCommand.phone =customerRegistrationCommand.phone
        return customerRegistrationRespondCommand
    }

    override fun fetchAllCustomers(): List<CustomerDomain> {
       return  listOf(CustomerDomain(1, "08101067538"), CustomerDomain(2, "08101057538"))
    }

    override fun countCustomer(): Int {
       return  1
    }

    override fun searchACustomerByNameOrPhone(searchTerm: String): List<CustomerDomain> {
       return  listOf(CustomerDomain(1, "08101067538"), CustomerDomain(2, "08101057538"))
    }
}


@Service
class  TransactionDomainGatewayImpl: TransactionGateway {
    override fun performCustomerTransaction(transactionRequestCommand: TransactionRequestCommand): TransactionDomain {
       val transactionDomain = TransactionDomain(1, transactionRequestCommand.amount, transactionRequestCommand.chargeType.calculateCharge())
           transactionDomain.transactionType = transactionDomain.transactionType
        return transactionDomain
    }
    override fun fetchAllCustomerTransaction(): List<TransactionDomain> {
       return  listOf(TransactionDomain(1, 1000.00,200.00), TransactionDomain(2, 20000.00,500.00))
    }
}