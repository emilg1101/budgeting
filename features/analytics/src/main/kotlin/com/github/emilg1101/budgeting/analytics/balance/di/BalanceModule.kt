package com.github.emilg1101.budgeting.analytics.balance.di

import androidx.lifecycle.ViewModel
import com.github.emilg1101.budgeting.analytics.balance.ui.BalanceViewModel
import com.github.emilg1101.budgeting.analytics.main.di.AnalyticsScope
import com.github.emilg1101.budgeting.core.di.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface BalanceModule {
    @Binds
    @IntoMap
    @ViewModelKey(BalanceViewModel::class)
    @AnalyticsScope
    fun provideBalanceViewModel(viewModel: BalanceViewModel): ViewModel
}
