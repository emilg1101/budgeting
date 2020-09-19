package com.github.emilg1101.budgeting.data.db.entity

import org.threeten.bp.OffsetDateTime

data class MonthBalance(
    val monthYear: OffsetDateTime,
    val amount: Long
)
