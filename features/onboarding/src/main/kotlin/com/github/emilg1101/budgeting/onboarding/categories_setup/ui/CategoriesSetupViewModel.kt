package com.github.emilg1101.budgeting.onboarding.categories_setup.ui

import androidx.lifecycle.asLiveData
import com.github.emilg1101.budgeting.core.base.BaseViewModel
import com.github.emilg1101.budgeting.core.mapFlatten
import com.github.emilg1101.budgeting.onboarding.categories_setup.domain.GetDefaultCategoriesUseCase
import com.github.emilg1101.budgeting.onboarding.di.OnboardingFlowScope
import com.github.emilg1101.budgeting.onboarding.model.CategoryModel
import com.github.emilg1101.budgeting.onboarding.model.SetupModel
import javax.inject.Inject

@OnboardingFlowScope
class CategoriesSetupViewModel @Inject constructor(
    private val getDefaultCategoriesUseCase: GetDefaultCategoriesUseCase,
    private val setupModel: SetupModel
) : BaseViewModel() {

    val categories =
        getDefaultCategoriesUseCase().mapFlatten { CategoryModel(it.emoji, it.name) }.asLiveData()

    fun onNextButtonClick() {
        setupModel.categories = categories.value ?: emptyList()
    }
}
