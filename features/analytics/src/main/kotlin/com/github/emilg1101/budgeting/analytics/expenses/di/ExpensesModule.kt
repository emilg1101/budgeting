package com.github.emilg1101.budgeting.analytics.expenses.di

import androidx.lifecycle.ViewModel
import com.github.emilg1101.budgeting.analytics.expenses.ui.ExpensesViewModel
import com.github.emilg1101.budgeting.analytics.main.di.AnalyticsScope
import com.github.emilg1101.budgeting.core.di.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ExpensesModule {
    @Binds
    @IntoMap
    @ViewModelKey(ExpensesViewModel::class)
    @AnalyticsScope
    fun provideExpensesViewModel(viewModel: ExpensesViewModel): ViewModel
}
