package com.github.emilg1101.budgeting.analytics.income.di

import androidx.lifecycle.ViewModel
import com.github.emilg1101.budgeting.analytics.income.ui.IncomeViewModel
import com.github.emilg1101.budgeting.analytics.main.di.AnalyticsScope
import com.github.emilg1101.budgeting.core.di.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface IncomeModule {
    @Binds
    @IntoMap
    @ViewModelKey(IncomeViewModel::class)
    @AnalyticsScope
    fun provideIncomeViewModel(viewModel: IncomeViewModel): ViewModel
}
