package com.tony

import com.tony.CalculatorException.InsufficientInputException
import com.tony.CalculatorException.InvalidInputException
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.*
import kotlin.collections.ArrayList

class RpnCalculator : AbstractCalculator() {

    private val operatorMap: Map<String, Operator> = Operator.values().toList().associateBy(
        { operator -> operator.token },
        { operator -> operator })
    private var stack: LinkedList<BigDecimal> = LinkedList()
    private var history: LinkedList<Collection<BigDecimal>> = LinkedList()
    private var warning = ""

    override fun feed(input: String) {
        warning = ""
        var token = ""
        var index = 0
        while (index < input.length + 1) {
            if (index == input.length || input[index].isWhitespace()) {
                try {
                    processSingleToken(token, position = index - token.length + 1)
                } catch (e: Exception) {
                    warning = e.message.toString()
                    return
                }
                token = ""
            } else {
                token += input[index]
            }
            index++
        }
    }

    override fun getResult(): String {
        val formatter = DecimalFormat("#.##########")
        formatter.roundingMode = RoundingMode.DOWN
        return stack.joinToString(separator = " ") { formatter.format(it) }
    }

    override fun getWarning(): String {
        return warning
    }

    override fun undo() {
        stack.removeLast()
        stack.addAll(history.removeLast().reversed())
    }

    override fun clear() {
        stack = LinkedList()
        history = LinkedList()
    }

    private fun processSingleToken(token: String, position: Int) {
        if (token.isBlank()) return
        if ("clear" == token) {
            clear()
            return
        }
        if ("undo" == token) {
            undo(position)
            return
        }
        operatorMap[token]?.let {
            process(it, position)
            return
        }
        try {
            val number = BigDecimal(token).setScale(15, RoundingMode.HALF_UP)
            stack.addLast(number)
            history.add(emptyList())
            return
        } catch (exception: NumberFormatException) {
        }
        throw InvalidInputException(token)
    }

    private fun process(operator: Operator, position: Int) {
        if (stack.size < operator.consume) {
            throw InsufficientInputException(operator = operator.token, pos = position.toString())
        }
        val inputs = ArrayList<BigDecimal>()
        while (inputs.size < operator.consume) {
            inputs.add(stack.removeLast())
        }
        val result = operator.calculate(inputs)
        stack.addLast(result)
        history.add(inputs)
    }

    private fun undo(position: Int) {
        if (history.isEmpty()) {
            throw InsufficientInputException("undo", position.toString())
        }
        undo()
    }
}