package com.github.emilg1101.budgeting.analytics.main.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.github.emilg1101.budgeting.analytics.main.domain.GetExpensesTransactionsUseCase
import com.github.emilg1101.budgeting.analytics.main.domain.GetIncomeTransactionsUseCase
import com.github.emilg1101.budgeting.analytics.main.domain.GetTransactionsUseCase
import com.github.emilg1101.budgeting.analytics.main.model.TransactionItem
import com.github.emilg1101.budgeting.core.base.BaseViewModel
import com.github.emilg1101.budgeting.core.mapFlatten
import kotlinx.coroutines.flow.map
import org.threeten.bp.OffsetDateTime
import javax.inject.Inject

class AnalyticsViewModel @Inject constructor(
    private val getTransactionsUseCase: GetTransactionsUseCase,
    private val getIncomeTransactionsUseCase: GetIncomeTransactionsUseCase,
    private val getExpensesTransactionsUseCase: GetExpensesTransactionsUseCase
) : BaseViewModel() {

    private val _currentTab = MutableLiveData(0)

    val transactions = _currentTab.switchMap { tab ->
        when (tab) {
            0 -> getTransactionsUseCase()
            1 -> getIncomeTransactionsUseCase()
            else -> getExpensesTransactionsUseCase()
        }.mapFlatten {
            TransactionItem.TransactionModel(
                it.id,
                it.from.name,
                it.to.name,
                it.to.emoji,
                it.createdAt,
                it.amount
            )
        }.map { transactions ->
            transactions.groupBy { model ->
                model.date.let {
                    OffsetDateTime.of(it.year, it.monthValue, it.dayOfMonth, 0, 0, 0, 0, it.offset)
                }
            }.flatMap {
                listOf(TransactionItem.TransactionHeader(it.key), *(it.value).toTypedArray())
            }
        }.asLiveData()
    }

    fun setCurrentTab(position: Int) {
        _currentTab.value = position
    }
}
