package com.github.emilg1101.budgeting.transaction.ui.picker

import org.threeten.bp.OffsetDateTime

interface DateTimePickerCallback {
    fun onDateTimePicked(timestamp: OffsetDateTime)
}
