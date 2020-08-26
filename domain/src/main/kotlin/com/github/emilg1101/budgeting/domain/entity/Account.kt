package com.github.emilg1101.budgeting.domain.entity

import java.util.*

data class Account(
    override val id: Int,
    override val name: String,
    override val amount: Long,
    override val created: Date
) : ICategory
