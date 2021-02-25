package com.tony

abstract class CalculatorException(message: String?) : Exception(message) {

    class InvalidInputException(message: String) : CalculatorException("Invalid Input: $message")

    class InsufficientInputException(operator: String, pos: String) :
        CalculatorException("operator $operator (position: $pos): insufficient parameters")
}