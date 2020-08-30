package com.github.emilg1101.budgeting.home.domain

import com.github.emilg1101.budgeting.domain.repository.CategoryRepository
import javax.inject.Inject

class GetAccountsUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository
) {

    operator fun invoke() = categoryRepository.getAccounts()
}
