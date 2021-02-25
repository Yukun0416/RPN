package com.tony

abstract class AbstractCalculator {
    abstract fun feed(input: String)
    abstract fun getResult(): String
    abstract fun getWarning(): String
    abstract fun undo()
    abstract fun clear()
}