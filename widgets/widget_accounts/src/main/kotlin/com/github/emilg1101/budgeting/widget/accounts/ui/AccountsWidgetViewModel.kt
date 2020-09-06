package com.github.emilg1101.budgeting.widget.accounts.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.github.emilg1101.budgeting.widget.accounts.domain.GetAccountsUseCase
import javax.inject.Inject

class AccountsWidgetViewModel @Inject constructor(
    private val getAccountsUseCase: GetAccountsUseCase
) : ViewModel() {

    val accounts = getAccountsUseCase().asLiveData()
}
