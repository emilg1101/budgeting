package com.github.emilg1101.budgeting.create_account.domain

import com.github.emilg1101.budgeting.core.di.scope.FeatureScope
import com.github.emilg1101.budgeting.domain.BaseUseCase
import com.github.emilg1101.budgeting.domain.entity.Account
import com.github.emilg1101.budgeting.domain.entity.EmptyCategory
import com.github.emilg1101.budgeting.domain.entity.Income
import com.github.emilg1101.budgeting.domain.entity.IncomeTransaction
import com.github.emilg1101.budgeting.domain.repository.CategoryRepository
import com.github.emilg1101.budgeting.domain.repository.TransactionRepository
import kotlinx.coroutines.launch
import org.threeten.bp.OffsetDateTime
import javax.inject.Inject

@FeatureScope
class CreateAccountUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository,
    private val transactionRepository: TransactionRepository
) : BaseUseCase() {

    suspend operator fun invoke(params: Params) {
        launch {
            val createdAccountId = categoryRepository.createAccount(EmptyCategory(params.name))
            val to = categoryRepository.findCategory(createdAccountId)
            val from = categoryRepository.findCategory(1)
            transactionRepository.saveTransaction(
                IncomeTransaction(
                    0,
                    from as Income,
                    to as Account,
                    params.amount,
                    OffsetDateTime.now()
                )
            )
        }
    }

    data class Params(val name: String, val amount: Long)
}
