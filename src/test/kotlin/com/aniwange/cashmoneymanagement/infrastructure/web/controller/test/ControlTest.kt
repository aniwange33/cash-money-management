package com.aniwange.cashmoneymanagement.infrastructure.web.controller.test

import junit.framework.TestCase
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class ControlTest {

    @Test
    fun testHelloWord(){
        TestCase.assertEquals("hello amos", "hello amos")
    }
}