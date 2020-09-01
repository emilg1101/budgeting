package com.github.emilg1101.budgeting.onboarding.accounts_setup.di

import androidx.lifecycle.ViewModel
import com.github.emilg1101.budgeting.core.di.viewmodel.ViewModelKey
import com.github.emilg1101.budgeting.onboarding.accounts_setup.ui.AccountsSetupViewModel
import com.github.emilg1101.budgeting.onboarding.di.OnboardingFlowScope
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface AccountsSetupModule {
    @Binds
    @OnboardingFlowScope
    @IntoMap
    @ViewModelKey(AccountsSetupViewModel::class)
    fun provideAccountsSetupViewModel(viewModel: AccountsSetupViewModel): ViewModel
}
