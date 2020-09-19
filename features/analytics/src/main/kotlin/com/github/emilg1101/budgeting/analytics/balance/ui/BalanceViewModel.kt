package com.github.emilg1101.budgeting.analytics.balance.ui

import androidx.lifecycle.asLiveData
import com.github.emilg1101.budgeting.analytics.balance.domain.GetBalanceReportUseCase
import com.github.emilg1101.budgeting.analytics.balance.domain.GetTotalBalanceUseCase
import com.github.emilg1101.budgeting.core.base.BaseViewModel
import javax.inject.Inject

class BalanceViewModel @Inject constructor(
    private val getBalanceReportUseCase: GetBalanceReportUseCase,
    private val getTotalBalanceUseCase: GetTotalBalanceUseCase
) : BaseViewModel() {

    val report = getBalanceReportUseCase().asLiveData()

    val balance = getTotalBalanceUseCase().asLiveData()
}
