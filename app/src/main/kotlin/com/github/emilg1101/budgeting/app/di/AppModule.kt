package com.github.emilg1101.budgeting.app.di

import androidx.lifecycle.ViewModel
import com.github.emilg1101.budgeting.app.AppViewModel
import com.github.emilg1101.budgeting.core.di.scope.FeatureScope
import com.github.emilg1101.budgeting.core.di.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface AppModule {

    @Binds
    @IntoMap
    @FeatureScope
    @ViewModelKey(AppViewModel::class)
    fun provideAppViewModel(viewModel: AppViewModel): ViewModel
}
