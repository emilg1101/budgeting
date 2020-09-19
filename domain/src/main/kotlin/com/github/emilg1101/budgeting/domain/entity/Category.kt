package com.github.emilg1101.budgeting.domain.entity

import org.threeten.bp.OffsetDateTime

data class Category(
    override val id: Int,
    override val name: String,
    override val amount: Long,
    override val created: OffsetDateTime,
    override val emoji: String
) : ICategory
