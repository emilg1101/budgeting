package com.github.emilg1101.budgeting.domain.entity

import java.util.*

data class Category(
    override val id: Int,
    override val name: String,
    override val amount: Long,
    override val created: Date,
    val emoji: String
) : ICategory
