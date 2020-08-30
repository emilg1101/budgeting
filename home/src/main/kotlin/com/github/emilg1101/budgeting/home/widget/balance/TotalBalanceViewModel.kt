package com.github.emilg1101.budgeting.home.widget.balance

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.github.emilg1101.budgeting.home.domain.GetTotalBalanceUseCase
import javax.inject.Inject

class TotalBalanceViewModel @Inject constructor(
    private val getTotalBalanceUseCase: GetTotalBalanceUseCase
) : ViewModel() {

    val balance
        get() = getTotalBalanceUseCase().asLiveData()
}
