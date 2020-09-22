package com.github.emilg1101.budgeting.wallets.accounts.di

import androidx.lifecycle.ViewModel
import com.github.emilg1101.budgeting.core.di.viewmodel.ViewModelKey
import com.github.emilg1101.budgeting.wallets.accounts.ui.AccountsViewModel
import com.github.emilg1101.budgeting.wallets.main.di.AllAccountsScope
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface AccountsModule {

    @AllAccountsScope
    @Binds
    @IntoMap
    @ViewModelKey(AccountsViewModel::class)
    fun provideAccountsViewModel(viewModel: AccountsViewModel): ViewModel
}
