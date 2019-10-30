package com.aniwange.cashmoneymanagement

import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class CashMoneyManagementApplicationTests {

//	@Test
//	fun contextLoads() {
//	}

	@Test
	fun testString(){
		assertEquals("amos", "amos")
	}

}
