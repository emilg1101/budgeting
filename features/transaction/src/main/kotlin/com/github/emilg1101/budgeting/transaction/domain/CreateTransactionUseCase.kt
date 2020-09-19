package com.github.emilg1101.budgeting.transaction.domain

import com.github.emilg1101.budgeting.domain.entity.*
import com.github.emilg1101.budgeting.domain.repository.CategoryRepository
import com.github.emilg1101.budgeting.domain.repository.TransactionRepository
import org.threeten.bp.OffsetDateTime
import javax.inject.Inject

class CreateTransactionUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository,
    private val transactionRepository: TransactionRepository
) {
    suspend operator fun invoke(
        amount: Long,
        transactionType: TransactionType,
        date: OffsetDateTime,
        fromId: Int,
        toId: Int
    ) {
        val from = categoryRepository.findCategory(fromId)
        val to = categoryRepository.findCategory(toId)
        var isTransfer = false
        val transaction = when (transactionType) {
            is TransactionType.Expense -> ExpenseTransaction(
                0,
                from as Account,
                to as Category,
                amount * -1,
                date
            )
            is TransactionType.Income -> {
                if (from is Income) {
                    IncomeTransaction(0, from, to as Account, amount, date)
                } else {
                    isTransfer = true
                    TransferTransaction(0, from as Account, to as Account, amount, date)
                }
            }
        }
        transactionRepository.saveTransaction(transaction)
        if (isTransfer) {
            TransferTransaction(0, to as Account, from as Account, amount * -1, date).let {
                transactionRepository.saveTransaction(it)
            }
        }
    }
}

sealed class TransactionType {
    object Income : TransactionType()
    object Expense : TransactionType()
}
