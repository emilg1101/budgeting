package com.github.emilg1101.budgeting.onboarding.accounts_setup.di

import com.github.emilg1101.budgeting.onboarding.accounts_setup.ui.AccountsSetupFragment
import com.github.emilg1101.budgeting.onboarding.categories_setup.ui.CategoriesSetupFragment
import com.github.emilg1101.budgeting.onboarding.di.OnboardingFlowScope
import dagger.Subcomponent

@OnboardingFlowScope
@Subcomponent(modules = [AccountsSetupModule::class])
interface AccountsSetupSubcomponent {
    @Subcomponent.Builder
    interface Builder {
        fun build(): AccountsSetupSubcomponent
    }

    fun inject(fragment: AccountsSetupFragment)
}
