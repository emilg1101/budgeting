package com.github.emilg1101.budgeting.onboarding.di

import androidx.lifecycle.ViewModel
import com.github.emilg1101.budgeting.core.di.scope.FeatureScope
import com.github.emilg1101.budgeting.core.di.viewmodel.ViewModelKey
import com.github.emilg1101.budgeting.onboarding.model.SetupModel
import com.github.emilg1101.budgeting.onboarding.ui.OnboardingFlowViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
interface OnboardingModule {

    @Binds
    @FeatureScope
    @IntoMap
    @ViewModelKey(OnboardingFlowViewModel::class)
    fun provideOnboardingFlowViewModel(viewModel: OnboardingFlowViewModel): ViewModel

    @Module
    companion object {
        @Provides
        @JvmStatic
        @FeatureScope
        fun provideSetupModel() = SetupModel()
    }
}
