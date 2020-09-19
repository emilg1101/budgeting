package com.github.emilg1101.budgeting.domain.repository

import com.github.emilg1101.budgeting.domain.entity.Transaction
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {

    suspend fun saveTransaction(transaction: Transaction<*, *>)

    fun balance(): Flow<Long>

    fun getTransactions(): Flow<List<Transaction<*, *>>>

    fun getExpensesTransactions(): Flow<List<Transaction<*, *>>>

    fun getIncomeTransactions(): Flow<List<Transaction<*, *>>>
}
