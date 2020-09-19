package com.github.emilg1101.budgeting.analytics.expenses.ui

import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import com.github.emilg1101.budgeting.analytics.expenses.domain.GetExpensesUseCase
import com.github.emilg1101.budgeting.core.base.BaseViewModel
import javax.inject.Inject

class ExpensesViewModel @Inject constructor(
    private val getExpensesUseCase: GetExpensesUseCase
) : BaseViewModel() {

    val report = getExpensesUseCase().asLiveData()

    val totalExpenses = report.map { it.toList().first() }
}
