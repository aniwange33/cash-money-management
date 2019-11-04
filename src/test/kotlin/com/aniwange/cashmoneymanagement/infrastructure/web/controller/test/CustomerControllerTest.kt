package com.aniwange.cashmoneymanagement.infrastructure.web.controller.test

import com.aniwange.cashmoneymanagement.domain.CustomerDomain
import com.aniwange.cashmoneymanagement.domain.model.CustomerRegistrationCommand
import com.aniwange.cashmoneymanagement.domain.model.Name
import com.aniwange.cashmoneymanagement.infrastructure.entities.Customer
import com.aniwange.cashmoneymanagement.infrastructure.gatewayImp.convertCustomerToCustomerDomain
import com.aniwange.cashmoneymanagement.infrastructure.web.controller.CustomerController
import com.aniwange.cashmoneymanagement.usecases.FetchAllCustomers
import com.aniwange.cashmoneymanagement.usecases.RegisterACustomer
import com.aniwange.cashmoneymanagement.usecases.SearchACustomerByNameOrPhone
import com.google.gson.Gson
import org.hamcrest.Matchers
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.internal.verification.Times
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@ExtendWith(MockitoExtension::class)
class CustomerControllerTest(@Mock  val registerACustomer: RegisterACustomer){
    @Mock
    lateinit var fetchAllCustomers: FetchAllCustomers
    @Mock
    lateinit var searchACustomerByNameOrPhone: SearchACustomerByNameOrPhone
    private var  mockMvc: MockMvc? = null


    @BeforeEach
    fun setUp(){
         val  customerController  =  CustomerController(registerACustomer, fetchAllCustomers, searchACustomerByNameOrPhone)
         mockMvc = MockMvcBuilders.standaloneSetup(customerController).build()

    }

    @Test
    fun registerACustomerTest(){
        val request = CustomerRegistrationCommand("08101067538","tertese@gmail.com")
        val name = Name("Aniwange","Tertese","Amos")
        request.name = name
        val customer = Customer(name.surname)
        customer.phoneNumber = request.phone
        customer.firstName = name.middleName?: ""
        val gson = Gson()
        val requestString = gson.toJson(request)
       `when`(registerACustomer.registerACustomer(request)).thenReturn(convertCustomerToCustomerDomain(customer))
        val mvcResult= mockMvc!!.perform(MockMvcRequestBuilders.post("/v1/api/app/customer")
                .header("client-key","test")
                .header("Authorization", "dhjdjdjdiwnsnsj")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestString))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.`is`("${customer.surname}  ${customer.firstName}")))
                .andReturn()
        verify(registerACustomer, Times(1)).registerACustomer(request)
    }

    @Test
    fun searchACustomerByNameOrPhoneTest(){
        val customerD = CustomerDomain(1, "08101067538")
           customerD.name = "Jerry Okafor"
        val customerD1 = CustomerDomain(1, "08101067538")
            customerD.name = "Jerry tyowar"
        `when`(searchACustomerByNameOrPhone.searchCustomerByNameOrPhone("jerry")).thenReturn(listOf(customerD,customerD1))
         val mvcResult= mockMvc!!.perform(MockMvcRequestBuilders.get("/v1/api/app/customer/search/jerry")
                .header("client-key","test")
                .header("Authorization", "dhjdjdjdiwnsnsj")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("jerry"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andReturn()
        println(mvcResult.response.contentAsString)
        verify(searchACustomerByNameOrPhone, Times(1)).searchCustomerByNameOrPhone("jerry")
    }

    @Test
    fun listAllCustomersTest(){
        val customerD = CustomerDomain(1, "08101067538")
        customerD.name = "Jerry Okafor"
        val customerD1 = CustomerDomain(1, "08101067538")
        customerD.name = "Jerry tyowar"
        `when`(fetchAllCustomers.fetchAllCustomers()).thenReturn(listOf(customerD,customerD1))
        val mvcResult= mockMvc!!.perform(MockMvcRequestBuilders.get("/v1/api/app/customer/list")
                .header("client-key","test")
                .header("Authorization", "dhjdjdjdiwnsnsj")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andReturn()
        println(mvcResult.response.contentAsString)
        verify(fetchAllCustomers, Times(1)).fetchAllCustomers()
    }

}