package com.github.emilg1101.budgeting.analytics.balance.domain

import com.github.emilg1101.budgeting.domain.BaseUseCase
import com.github.emilg1101.budgeting.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetTotalBalanceUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
) : BaseUseCase() {

    operator fun invoke() = transactionRepository.balance().flowOn(ioDispatcher)
}
