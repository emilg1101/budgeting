package com.github.emilg1101.budgeting.domain.entity

import org.threeten.bp.OffsetDateTime

abstract class Transaction<From : ICategory, To : ICategory> {
    abstract val id: Int
    abstract val from: From
    abstract val to: To
    abstract val amount: Long
    abstract val createdAt: OffsetDateTime
}

data class TransferTransaction(
    override val id: Int,
    override val from: Account,
    override val to: Account,
    override val amount: Long,
    override val createdAt: OffsetDateTime
) : Transaction<Account, Account>()

data class IncomeTransaction(
    override val id: Int,
    override val from: Income,
    override val to: Account,
    override val amount: Long,
    override val createdAt: OffsetDateTime

) : Transaction<Income, Account>()

data class ExpenseTransaction(
    override val id: Int,
    override val from: Account,
    override val to: Category,
    override val amount: Long,
    override val createdAt: OffsetDateTime
) : Transaction<Account, Category>()
