package com.tony

import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue


internal class RpnCalculatorTest {
    lateinit var calculator: RpnCalculator

    @Before
    fun setUp() {
        calculator = RpnCalculator()
    }

    @Test
    fun test1() {
        calculator.feed("5 2")
        assertTrue(calculator.getWarning().isBlank())
        assertEquals("5 2", calculator.getResult())
    }

    @Test
    fun test2() {
        calculator.feed("2 sqrt")
        assertTrue(calculator.getWarning().isBlank())
        assertEquals("1.4142135623", calculator.getResult())

        calculator.feed("clear 9 sqrt")
        assertTrue(calculator.getWarning().isBlank())
        assertEquals("3", calculator.getResult())
    }

    @Test
    fun test3() {
        calculator.feed("5 2 -")
        assertTrue(calculator.getWarning().isBlank())
        assertEquals("3", calculator.getResult())

        calculator.feed("3 -")
        assertTrue(calculator.getWarning().isBlank())
        assertEquals("0", calculator.getResult())

        calculator.feed("clear")
        assertTrue(calculator.getWarning().isBlank())
        assertEquals("", calculator.getResult())
    }

    @Test
    fun test4() {
        calculator.feed("5 4 3 2")
        assertTrue(calculator.getWarning().isBlank())
        assertEquals("5 4 3 2", calculator.getResult())

        calculator.feed("undo undo *")
        assertTrue(calculator.getWarning().isBlank())
        assertEquals("20", calculator.getResult())

        calculator.feed("5 *")
        assertTrue(calculator.getWarning().isBlank())
        assertEquals("100", calculator.getResult())

        calculator.feed("undo")
        assertTrue(calculator.getWarning().isBlank())
        assertEquals("20 5", calculator.getResult())
    }

    @Test
    fun test5() {
        calculator.feed("7 12 2 /")
        assertTrue(calculator.getWarning().isBlank())
        assertEquals("7 6", calculator.getResult())

        calculator.feed("*")
        assertTrue(calculator.getWarning().isBlank())
        assertEquals("42", calculator.getResult())

        calculator.feed("4 /")
        assertTrue(calculator.getWarning().isBlank())
        assertEquals("10.5", calculator.getResult())
    }

    @Test
    fun test6() {
        calculator.feed("1 2 3 4 5")
        assertTrue(calculator.getWarning().isBlank())
        assertEquals("1 2 3 4 5", calculator.getResult())

        calculator.feed("*")
        assertTrue(calculator.getWarning().isBlank())
        assertEquals("1 2 3 20", calculator.getResult())

        calculator.feed("clear 3 4 -")
        assertTrue(calculator.getWarning().isBlank())
        assertEquals("-1", calculator.getResult())
    }

    @Test
    fun test7() {
        calculator.feed("1 2 3 4 5")
        assertTrue(calculator.getWarning().isBlank())
        assertEquals("1 2 3 4 5", calculator.getResult())

        calculator.feed("* * * *")
        assertTrue(calculator.getWarning().isBlank())
        assertEquals("120", calculator.getResult())
    }

    @Test
    fun test8() {
        calculator.feed("1 2 3 * 5 + * * 6 5")
        assertEquals("operator * (position: 15): insufficient parameters", calculator.getWarning())
        assertEquals("11", calculator.getResult())
    }
}