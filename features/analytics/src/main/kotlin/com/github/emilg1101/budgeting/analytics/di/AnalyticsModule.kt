package com.github.emilg1101.budgeting.analytics.di

import androidx.lifecycle.ViewModel
import com.github.emilg1101.budgeting.analytics.AnalyticsViewModel
import com.github.emilg1101.budgeting.core.di.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface AnalyticsModule {

    @Binds
    @IntoMap
    @ViewModelKey(AnalyticsViewModel::class)
    fun provideAnalyticsViewModel(viewModel: AnalyticsViewModel): ViewModel
}
