package com.github.emilg1101.budgeting.onboarding.accounts_setup.domain

import com.github.emilg1101.budgeting.domain.entity.EmptyCategory
import com.github.emilg1101.budgeting.domain.repository.CategoryRepository
import com.github.emilg1101.budgeting.onboarding.di.OnboardingFlowScope
import kotlinx.coroutines.*
import javax.inject.Inject

@OnboardingFlowScope
class SetupUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository
) {

    suspend operator fun invoke(categories: List<EmptyCategory>, accounts: List<EmptyCategory>) {
        coroutineScope {
            async { categoryRepository.createCategories(categories) }
            async { categoryRepository.createAccounts(accounts) }
        }
    }
}
