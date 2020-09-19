package com.github.emilg1101.budgeting.analytics.main.domain

import com.github.emilg1101.budgeting.domain.BaseUseCase
import com.github.emilg1101.budgeting.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

class GetIncomeTransactionsUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
) : BaseUseCase() {
    operator fun invoke() = transactionRepository.getIncomeTransactions().flowOn(ioDispatcher)
}
