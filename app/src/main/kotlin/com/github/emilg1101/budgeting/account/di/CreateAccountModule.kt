package com.github.emilg1101.budgeting.account.di

import androidx.lifecycle.ViewModel
import com.github.emilg1101.budgeting.account.ui.CreateAccountViewModel
import com.github.emilg1101.budgeting.core.di.scope.FeatureScope
import com.github.emilg1101.budgeting.core.di.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface CreateAccountModule {

    @Binds
    @FeatureScope
    @IntoMap
    @ViewModelKey(CreateAccountViewModel::class)
    fun provideCreateAccountViewModel(viewModel: CreateAccountViewModel): ViewModel
}
