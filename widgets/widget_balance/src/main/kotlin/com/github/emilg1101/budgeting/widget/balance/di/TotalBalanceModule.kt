package com.github.emilg1101.budgeting.widget.balance.di

import androidx.lifecycle.ViewModel
import com.github.emilg1101.budgeting.core.di.viewmodel.ViewModelKey
import com.github.emilg1101.budgeting.widget.balance.ui.TotalBalanceViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface TotalBalanceModule {
    @Binds
    @IntoMap
    @ViewModelKey(TotalBalanceViewModel::class)
    fun provideTotalBalanceViewModel(viewModel: TotalBalanceViewModel): ViewModel
}
