package com.github.emilg1101.budgeting.data.db.entity

import org.threeten.bp.OffsetDateTime

data class CategoryEntityWithAmount(
    val id: Int,
    val name: String,
    val emoji: String,
    val createdAt: OffsetDateTime,
    val type: CategoryType,
    val visible: Int,
    var amount: Long
)
