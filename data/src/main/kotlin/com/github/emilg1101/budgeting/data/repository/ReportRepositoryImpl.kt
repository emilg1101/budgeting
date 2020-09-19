package com.github.emilg1101.budgeting.data.repository

import com.github.emilg1101.budgeting.data.db.dao.TransactionDao
import com.github.emilg1101.budgeting.data.withYearMonth
import com.github.emilg1101.budgeting.domain.repository.ReportRepository
import kotlinx.coroutines.flow.*
import org.threeten.bp.OffsetDateTime
import javax.inject.Inject
import kotlin.math.abs

class ReportRepositoryImpl @Inject constructor(
    private val transactionDao: TransactionDao
) : ReportRepository {

    override fun getBalanceYearReport(): Flow<Map<OffsetDateTime, Long>> {
        return transactionDao.getTransactionsAmountByMonth().map {
            val lastTransaction = transactionDao.getLastTransaction()?.createdAt ?: OffsetDateTime.now()
            val yearReport = createEmptyYearReport(lastTransaction.withYearMonth())
            var totalBalance = transactionDao.balance().first() ?: 0L
            it.take(12).forEach {
                yearReport[it.monthYear.withYearMonth()] = totalBalance
                totalBalance -= it.amount
            }
            yearReport
        }
    }

    override fun getTotalIncomeYearReport(): Flow<Map<OffsetDateTime, Long>> {
        return transactionDao.getIncomeTransactionsAmountByMonth().map {
            val yearReport = createEmptyYearReport(OffsetDateTime.now().withYearMonth())
            it.take(12).forEach {
                yearReport[it.monthYear.withYearMonth()] = it.amount
            }
            yearReport
        }
    }

    override fun getTotalExpensesYearReport(): Flow<Map<OffsetDateTime, Long>> {
        return transactionDao.getExpensesTransactionsAmountByMonth().map {
            val yearReport = createEmptyYearReport(OffsetDateTime.now().withYearMonth())
            it.take(12).forEach {
                yearReport[it.monthYear.withYearMonth()] = abs(it.amount)
            }
            yearReport
        }
    }

    private fun createEmptyYearReport(end: OffsetDateTime): MutableMap<OffsetDateTime, Long> {
        val emptyMap = mutableMapOf<OffsetDateTime, Long>()
        for (i in 0..11L) {
            emptyMap[end.minusMonths(i)] = 0
        }
        return emptyMap
    }
}
