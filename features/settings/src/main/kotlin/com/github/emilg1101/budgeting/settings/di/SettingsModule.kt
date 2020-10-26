package com.github.emilg1101.budgeting.settings.di

import androidx.lifecycle.ViewModel
import com.github.emilg1101.budgeting.core.di.scope.FeatureScope
import com.github.emilg1101.budgeting.core.di.viewmodel.ViewModelKey
import com.github.emilg1101.budgeting.settings.ui.SettingsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface SettingsModule {

    @Binds
    @IntoMap
    @FeatureScope
    @ViewModelKey(SettingsViewModel::class)
    fun provideSettingsViewModel(viewModel: SettingsViewModel): ViewModel
}
