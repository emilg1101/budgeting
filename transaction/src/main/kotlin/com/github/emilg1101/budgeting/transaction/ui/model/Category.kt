package com.github.emilg1101.budgeting.transaction.ui.model

open class BaseCategory(open val name: String) {
    var enabled = true
}

data class Category(override val name: String, val emoji: String) : BaseCategory(name)

data class Account(override val name: String, val amount: Long) : BaseCategory(name)
