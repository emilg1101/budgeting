package com.github.emilg1101.budgeting.domain.repository

import kotlinx.coroutines.flow.Flow
import org.threeten.bp.OffsetDateTime

interface ReportRepository {

    fun getBalanceYearReport(): Flow<Map<OffsetDateTime, Long>>

    fun getTotalIncomeYearReport(): Flow<Map<OffsetDateTime, Long>>

    fun getTotalExpensesYearReport(): Flow<Map<OffsetDateTime, Long>>
}
