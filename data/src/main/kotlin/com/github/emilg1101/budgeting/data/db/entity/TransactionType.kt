package com.github.emilg1101.budgeting.data.db.entity

enum class TransactionType(val type: Int) {
    EXPENSE(0),
    TRANSFER(1),
    INCOME(2)
}
