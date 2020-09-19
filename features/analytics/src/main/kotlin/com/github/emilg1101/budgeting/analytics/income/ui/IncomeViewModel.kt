package com.github.emilg1101.budgeting.analytics.income.ui

import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import com.github.emilg1101.budgeting.analytics.income.domain.GetIncomeUseCase
import com.github.emilg1101.budgeting.core.base.BaseViewModel
import javax.inject.Inject

class IncomeViewModel @Inject constructor(
    private val getIncomeUseCase: GetIncomeUseCase
) : BaseViewModel() {

    val report = getIncomeUseCase().asLiveData()

    val totalIncome = report.map { it.toList().first() }
}
