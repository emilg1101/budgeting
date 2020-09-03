package com.github.emilg1101.budgeting.onboarding.accounts_setup.domain

import com.github.emilg1101.budgeting.domain.repository.CategoryRepository
import com.github.emilg1101.budgeting.onboarding.di.OnboardingFlowScope
import javax.inject.Inject

@OnboardingFlowScope
class GetDefaultAccountsUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository
) {
    operator fun invoke() = categoryRepository.defaultAccounts()
}
