package com.github.emilg1101.budgeting.widget.accounts.di

import androidx.lifecycle.ViewModel
import com.github.emilg1101.budgeting.core.di.viewmodel.ViewModelKey
import com.github.emilg1101.budgeting.widget.accounts.ui.AccountsWidgetViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface AccountsModule {
    @Binds
    @IntoMap
    @ViewModelKey(AccountsWidgetViewModel::class)
    fun provideAccountsWidgetViewModel(viewModel: AccountsWidgetViewModel): ViewModel
}
