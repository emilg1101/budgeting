package com.github.emilg1101.budgeting.analytics.balance.domain

import com.github.emilg1101.budgeting.domain.BaseUseCase
import com.github.emilg1101.budgeting.domain.repository.ReportRepository
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetBalanceReportUseCase @Inject constructor(
    private val reportRepository: ReportRepository
) : BaseUseCase() {
    operator fun invoke() = reportRepository.getBalanceYearReport().flowOn(ioDispatcher)
}
