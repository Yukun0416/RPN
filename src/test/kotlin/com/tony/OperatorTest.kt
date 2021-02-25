package com.tony

import com.tony.Operator.*
import org.junit.Test
import java.math.BigDecimal
import kotlin.test.assertEquals


internal class OperatorTest {
    @Test
    fun additionTest() {
        assertEquals(
            BigDecimal(8.1),
            ADDITION.calculate(listOf(BigDecimal(3), BigDecimal(5.1))),
            "test ADDITION"
        )
    }

    @Test
    fun subtractionTest() {
        assertEquals(
            BigDecimal(1),
            SUBTRACTION.calculate(listOf(BigDecimal(3), BigDecimal(4))),
            "test SUBTRACTION"
        )
    }

    @Test
    fun multiplicationTest() {
        assertEquals(
            BigDecimal(32),
            MULTIPLICATION.calculate(listOf(BigDecimal(8), BigDecimal(4))),
            "test MULTIPLICATION"
        )
    }

    @Test
    fun divisionTest() {
        assertEquals(
            BigDecimal(3),
            DIVISION.calculate(listOf(BigDecimal(2), BigDecimal(6))),
            "test DIVISION"
        )
    }

    @Test
    fun sqrtTest() {
        assertEquals(
            BigDecimal(12),
            SQRT.calculate(listOf(BigDecimal(144))),
            "test SQRT"
        )
    }
}