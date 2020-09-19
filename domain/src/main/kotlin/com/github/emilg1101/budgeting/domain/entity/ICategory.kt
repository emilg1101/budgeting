package com.github.emilg1101.budgeting.domain.entity

import org.threeten.bp.OffsetDateTime

interface ICategory {
    val id: Int
    val name: String
    val amount: Long
    val created: OffsetDateTime
    val emoji: String
}

class EmptyCategory(override val name: String, override val emoji: String = "") : ICategory {
    override val amount: Long
        get() = 0
    override val created: OffsetDateTime
        get() = OffsetDateTime.now()
    override val id: Int
        get() = 0
}
