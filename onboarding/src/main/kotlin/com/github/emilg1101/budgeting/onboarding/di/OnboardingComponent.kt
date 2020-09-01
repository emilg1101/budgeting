package com.github.emilg1101.budgeting.onboarding.di

import com.github.emilg1101.budgeting.core.di.component.CoreComponent
import com.github.emilg1101.budgeting.core.di.scope.FeatureScope
import com.github.emilg1101.budgeting.onboarding.accounts_setup.di.AccountsSetupSubcomponent
import com.github.emilg1101.budgeting.onboarding.categories_setup.di.CategoriesSetupSubcomponent
import com.github.emilg1101.budgeting.onboarding.ui.OnboardingFlowFragment
import dagger.Component

@FeatureScope
@Component(dependencies = [CoreComponent::class], modules = [OnboardingModule::class])
interface OnboardingComponent {
    fun inject(fragment: OnboardingFlowFragment)

    fun categoriesSetupSubcomponent(): CategoriesSetupSubcomponent.Builder
    fun accountsSetupSubcomponent(): AccountsSetupSubcomponent.Builder
}
