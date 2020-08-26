package com.github.emilg1101.budgeting.account.domain

import com.github.emilg1101.budgeting.core.di.scope.FeatureScope
import com.github.emilg1101.budgeting.domain.entity.EmptyCategory
import com.github.emilg1101.budgeting.domain.repository.CategoryRepository
import javax.inject.Inject

@FeatureScope
class CreateAccountUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository
) {

    suspend operator fun invoke(params: Params) {
        categoryRepository.createAccount(EmptyCategory(params.name))
    }

    data class Params(val name: String, val amount: Long)
}
