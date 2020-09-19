package com.github.emilg1101.budgeting.data.repository

import com.github.emilg1101.budgeting.data.db.dao.TransactionDao
import com.github.emilg1101.budgeting.data.db.entity.TransactionAndCategoriesEntity
import com.github.emilg1101.budgeting.data.db.entity.TransactionEntity
import com.github.emilg1101.budgeting.data.db.entity.TransactionType
import com.github.emilg1101.budgeting.data.mapFlatten
import com.github.emilg1101.budgeting.domain.entity.*
import com.github.emilg1101.budgeting.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val transactionDao: TransactionDao
) : TransactionRepository {

    override suspend fun saveTransaction(transaction: Transaction<*, *>) {
        val entity = TransactionEntity(
            transaction.id,
            transaction.from.id,
            transaction.to.id,
            transaction.amount,
            transaction.createdAt,
            when (transaction) {
                is IncomeTransaction -> TransactionType.INCOME
                is TransferTransaction -> TransactionType.TRANSFER
                else -> TransactionType.EXPENSE
            }
        )
        transactionDao.insert(entity)
    }

    override fun balance(): Flow<Long> {
        return transactionDao.balance().map { it ?: 0L }
    }

    override fun getTransactions(): Flow<List<Transaction<*, *>>> {
        return transactionDao.getTransactions().mapFlatten(transactionsMapper)
    }

    override fun getExpensesTransactions(): Flow<List<Transaction<*, *>>> {
        return transactionDao.getExpensesTransactions().mapFlatten(transactionsMapper)
    }

    override fun getIncomeTransactions(): Flow<List<Transaction<*, *>>> {
        return transactionDao.getIncomeTransactions().mapFlatten(transactionsMapper)
    }

    private val transactionsMapper: TransactionAndCategoriesEntity.() -> Transaction<*, *> = {
        when (transactionEntity.type) {
            TransactionType.EXPENSE -> ExpenseTransaction(
                transactionEntity.id,
                Account(from.id, from.name, 0L, from.createdAt, from.emoji),
                Category(to.id, to.name, 0L, to.createdAt, to.emoji),
                transactionEntity.amount,
                transactionEntity.createdAt
            )
            TransactionType.TRANSFER -> TransferTransaction(
                transactionEntity.id,
                Account(from.id, from.name, 0L, from.createdAt, from.emoji),
                Account(to.id, to.name, 0L, to.createdAt, to.emoji),
                transactionEntity.amount,
                transactionEntity.createdAt
            )
            TransactionType.INCOME -> IncomeTransaction(
                transactionEntity.id,
                Income(from.id, from.name, 0L, from.createdAt, from.emoji),
                Account(to.id, to.name, 0L, to.createdAt, to.emoji),
                transactionEntity.amount,
                transactionEntity.createdAt
            )
        }
    }
}
