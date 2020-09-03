package com.github.emilg1101.budgeting.authorization.di

import androidx.lifecycle.ViewModel
import com.github.emilg1101.budgeting.authorization.ui.AuthorizationViewModel
import com.github.emilg1101.budgeting.core.di.scope.FeatureScope
import com.github.emilg1101.budgeting.core.di.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface AuthorizationModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthorizationViewModel::class)
    @FeatureScope
    fun provideAuthorizationViewModel(viewModel: AuthorizationViewModel): ViewModel
}
