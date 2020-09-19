package com.github.emilg1101.budgeting.analytics.formatter

import com.github.emilg1101.budgeting.core.toCurrency
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.ValueFormatter
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.format.DateTimeFormatter

class XAxisValueFormatter(private val formatter: DateTimeFormatter) : ValueFormatter() {
    var items: List<OffsetDateTime>? = null

    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        if (value == -1f || value == 12f) return ""
        return items?.reversed()?.get(value.toInt())?.format(formatter) ?: ""
    }

    override fun getFormattedValue(value: Float): String {
        return value.toLong().toCurrency()
    }
}
