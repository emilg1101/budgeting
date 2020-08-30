package com.github.emilg1101.budgeting.home.widget.accounts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.github.emilg1101.budgeting.home.domain.GetAccountsUseCase
import javax.inject.Inject

class AccountsWidgetViewModel @Inject constructor(
    private val getAccountsUseCase: GetAccountsUseCase
) : ViewModel() {

    val accounts = getAccountsUseCase().asLiveData()
}
