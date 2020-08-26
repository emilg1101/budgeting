package com.github.emilg1101.budgeting.category.domain

import com.github.emilg1101.budgeting.core.di.scope.FeatureScope
import com.github.emilg1101.budgeting.domain.entity.EmptyCategory
import com.github.emilg1101.budgeting.domain.repository.CategoryRepository
import javax.inject.Inject

@FeatureScope
class CreateCategoryUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository
) {

    suspend operator fun invoke(params: Params) {
        categoryRepository.createCategory(EmptyCategory(params.name, params.emoji))
    }

    data class Params(val name: String, val emoji: String)
}
