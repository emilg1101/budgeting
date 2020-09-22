package com.github.emilg1101.budgeting.wallets.categories.domain

import com.github.emilg1101.budgeting.domain.BaseUseCase
import com.github.emilg1101.budgeting.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository
) : BaseUseCase() {

    operator fun invoke() = categoryRepository.getCategories().flowOn(ioDispatcher)
}
