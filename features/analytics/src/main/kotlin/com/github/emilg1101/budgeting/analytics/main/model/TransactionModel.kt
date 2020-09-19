package com.github.emilg1101.budgeting.analytics.main.model

import org.threeten.bp.OffsetDateTime
import java.util.*

sealed class TransactionItem {
    data class TransactionModel(
        val id: Int,
        val from: String,
        val to: String,
        val toEmoji: String,
        val date: OffsetDateTime,
        val amount: Long
    ) : TransactionItem()

    data class TransactionHeader(
        val date: OffsetDateTime
    ) : TransactionItem()
}
