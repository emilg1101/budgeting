package com.github.emilg1101.budgeting.core

import java.text.NumberFormat

fun Long?.toCurrency(symbol: String = "₽", isFloat: Boolean = true): String {
    val formatted = NumberFormat.getNumberInstance().apply {
        maximumFractionDigits = if (isFloat) 2 else 0
    }.format((this?.toFloat() ?: 0f) / 100f).replace(",", " ")
    return "$formatted $symbol".trim()
}
