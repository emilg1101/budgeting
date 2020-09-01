package com.github.emilg1101.budgeting.onboarding.categories_setup.di

import com.github.emilg1101.budgeting.onboarding.categories_setup.ui.CategoriesSetupFragment
import com.github.emilg1101.budgeting.onboarding.di.OnboardingFlowScope
import dagger.Subcomponent

@OnboardingFlowScope
@Subcomponent(modules = [CategoriesSetupModule::class])
interface CategoriesSetupSubcomponent {
    @Subcomponent.Builder
    interface Builder {
        fun build(): CategoriesSetupSubcomponent
    }

    fun inject(fragment: CategoriesSetupFragment)
}
