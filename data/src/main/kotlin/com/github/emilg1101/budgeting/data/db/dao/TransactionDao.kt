package com.github.emilg1101.budgeting.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.github.emilg1101.budgeting.data.db.entity.MonthBalance
import com.github.emilg1101.budgeting.data.db.entity.TransactionAndCategoriesEntity
import com.github.emilg1101.budgeting.data.db.entity.TransactionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {

    @Insert
    suspend fun insert(transactionEntity: TransactionEntity)

    @Query("SELECT SUM(amount) FROM transactions")
    fun balance(): Flow<Long?>

    @Query("SELECT * FROM transactions ORDER BY createdAt DESC")
    fun getTransactions(): Flow<List<TransactionAndCategoriesEntity>>

    @Query("SELECT * FROM transactions ORDER BY createdAt DESC")
    fun getAllTransactions(): Flow<List<TransactionEntity>>

    @Query("SELECT * FROM transactions WHERE type = 0 ORDER BY createdAt DESC")
    fun getExpensesTransactions(): Flow<List<TransactionAndCategoriesEntity>>

    @Query("SELECT * FROM transactions WHERE type = 2 ORDER BY createdAt DESC")
    fun getIncomeTransactions(): Flow<List<TransactionAndCategoriesEntity>>

    @Query("SELECT createdAt as monthYear, SUM(amount) AS amount FROM transactions GROUP BY strftime(\"%m-%Y\", createdAt) ORDER BY createdAt DESC")
    fun getTransactionsAmountByMonth(): Flow<List<MonthBalance>>

    @Query("SELECT createdAt as monthYear, SUM(amount) AS amount FROM transactions WHERE type = 2 GROUP BY strftime(\"%m-%Y\", createdAt) ORDER BY createdAt DESC")
    fun getIncomeTransactionsAmountByMonth(): Flow<List<MonthBalance>>

    @Query("SELECT createdAt as monthYear, SUM(amount) AS amount FROM transactions WHERE type = 0 GROUP BY strftime(\"%m-%Y\", createdAt) ORDER BY createdAt DESC")
    fun getExpensesTransactionsAmountByMonth(): Flow<List<MonthBalance>>

    @Query("SELECT * FROM transactions ORDER BY createdAt DESC LIMIT 1")
    suspend fun getLastTransaction(): TransactionEntity?
}
