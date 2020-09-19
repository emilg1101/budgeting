package com.github.emilg1101.budgeting.onboarding.categories_setup.domain

import com.github.emilg1101.budgeting.domain.BaseUseCase
import com.github.emilg1101.budgeting.domain.repository.CategoryRepository
import com.github.emilg1101.budgeting.onboarding.di.OnboardingFlowScope
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@OnboardingFlowScope
class GetDefaultCategoriesUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository
) : BaseUseCase() {
    operator fun invoke() = categoryRepository.defaultCategories().flowOn(ioDispatcher)
}
