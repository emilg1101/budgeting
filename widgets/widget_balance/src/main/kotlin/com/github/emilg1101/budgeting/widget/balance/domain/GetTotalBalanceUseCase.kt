package com.github.emilg1101.budgeting.widget.balance.domain

import com.github.emilg1101.budgeting.domain.repository.TransactionRepository
import javax.inject.Inject

class GetTotalBalanceUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
) {

    operator fun invoke() = transactionRepository.balance()
}
