package com.github.emilg1101.budgeting.wallets.accounts.domain

import com.github.emilg1101.budgeting.domain.BaseUseCase
import com.github.emilg1101.budgeting.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetAccountsUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository
) : BaseUseCase() {

    operator fun invoke() = categoryRepository.getAccounts().flowOn(ioDispatcher)
}
