package com.github.emilg1101.budgeting.domain.entity

import java.util.*

interface ICategory {
    val id: Int
    val name: String
    val amount: Long
    val created: Date
}

class EmptyCategory(override val name: String, val emoji: String = ""): ICategory {
    override val amount: Long
        get() = 0
    override val created: Date
        get() = Date()
    override val id: Int
        get() = 0
}
