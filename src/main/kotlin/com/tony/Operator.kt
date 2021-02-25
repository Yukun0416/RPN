package com.tony

import java.math.BigDecimal
import java.math.MathContext

enum class Operator(val token: String, val consume: Int) : Operation {
    ADDITION("+", 2) {
        override fun calculate(inputs: List<BigDecimal>): BigDecimal {
            return inputs[1].plus(inputs[0])
        }

    },
    SUBTRACTION("-", 2) {
        override fun calculate(inputs: List<BigDecimal>): BigDecimal {
            return inputs[1].minus(inputs[0])
        }

    },
    MULTIPLICATION("*", 2) {
        override fun calculate(inputs: List<BigDecimal>): BigDecimal {
            return inputs[1].times(inputs[0])
        }

    },
    DIVISION("/", 2) {
        override fun calculate(inputs: List<BigDecimal>): BigDecimal {
            return inputs[1].divide(inputs[0])
        }
    },
    SQRT("sqrt", 1) {
        override fun calculate(inputs: List<BigDecimal>): BigDecimal {
            return inputs[0].sqrt(MathContext.DECIMAL64)
        }
    };

}