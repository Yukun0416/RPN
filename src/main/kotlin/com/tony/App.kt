package com.tony

import java.util.*

class App {
    fun startCalculator() {
        val calculator = RpnCalculator()
        val sc = Scanner(System.`in`)
        while (true) {
            println("waiting input:")
            val input = sc.nextLine()
            if (input == "quit") break
            calculator.feed(input)
            if (calculator.getWarning().isNotBlank()) println(calculator.getWarning())
            println("stack: " + calculator.getResult())
        }
    }
}

fun main() {
    App().startCalculator()
}

