package com.github.emilg1101.budgeting.onboarding.categories_setup.di

import androidx.lifecycle.ViewModel
import com.github.emilg1101.budgeting.core.di.viewmodel.ViewModelKey
import com.github.emilg1101.budgeting.onboarding.adapter.CategoryAdapter
import com.github.emilg1101.budgeting.onboarding.categories_setup.ui.CategoriesSetupViewModel
import com.github.emilg1101.budgeting.onboarding.di.OnboardingFlowScope
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
interface CategoriesSetupModule {
    @Binds
    @OnboardingFlowScope
    @IntoMap
    @ViewModelKey(CategoriesSetupViewModel::class)
    fun provideCategoriesSetupViewModel(viewModel: CategoriesSetupViewModel): ViewModel

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideCategoryAdapter() = CategoryAdapter()
    }
}
