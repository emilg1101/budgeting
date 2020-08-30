package com.github.emilg1101.budgeting.home.di

import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.github.emilg1101.budgeting.core.di.viewmodel.ViewModelKey
import com.github.emilg1101.budgeting.home.adapter.WidgetAdapter
import com.github.emilg1101.budgeting.home.ui.HomeFragment
import com.github.emilg1101.budgeting.home.ui.HomeViewModel
import com.github.emilg1101.budgeting.home.widget.accounts.AccountsWidgetViewModel
import com.github.emilg1101.budgeting.home.widget.balance.TotalBalanceViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
interface HomeModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun provideHomeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TotalBalanceViewModel::class)
    fun provideTotalBalanceViewModel(viewModel: TotalBalanceViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AccountsWidgetViewModel::class)
    fun provideAccountsWidgetViewModel(viewModel: AccountsWidgetViewModel): ViewModel
}
