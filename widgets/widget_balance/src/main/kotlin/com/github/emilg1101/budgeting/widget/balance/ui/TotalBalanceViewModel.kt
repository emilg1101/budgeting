package com.github.emilg1101.budgeting.widget.balance.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.github.emilg1101.budgeting.widget.balance.domain.GetTotalBalanceUseCase
import javax.inject.Inject

class TotalBalanceViewModel @Inject constructor(
    private val getTotalBalanceUseCase: GetTotalBalanceUseCase
) : ViewModel() {

    val balance
        get() = getTotalBalanceUseCase().asLiveData()
}
