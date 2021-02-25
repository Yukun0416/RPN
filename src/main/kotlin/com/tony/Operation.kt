package com.tony

import java.math.BigDecimal

interface Operation {
    fun calculate(inputs: List<BigDecimal>): BigDecimal
}