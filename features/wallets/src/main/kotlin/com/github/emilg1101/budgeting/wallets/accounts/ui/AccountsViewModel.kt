package com.github.emilg1101.budgeting.wallets.accounts.ui

import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import com.github.emilg1101.budgeting.core.base.BaseViewModel
import com.github.emilg1101.budgeting.wallets.accounts.domain.GetAccountsUseCase
import javax.inject.Inject

class AccountsViewModel @Inject constructor(
    getAccountsUseCase: GetAccountsUseCase
) : BaseViewModel() {

    val accounts = getAccountsUseCase().asLiveData()

    val stubVisibility = accounts.map { it.isNullOrEmpty() }
}
