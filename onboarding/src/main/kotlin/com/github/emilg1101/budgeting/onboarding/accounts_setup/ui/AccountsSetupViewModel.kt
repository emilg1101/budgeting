package com.github.emilg1101.budgeting.onboarding.accounts_setup.ui

import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.github.emilg1101.budgeting.core.base.BaseViewModel
import com.github.emilg1101.budgeting.core.mapFlatten
import com.github.emilg1101.budgeting.domain.entity.EmptyCategory
import com.github.emilg1101.budgeting.onboarding.accounts_setup.domain.GetDefaultAccountsUseCase
import com.github.emilg1101.budgeting.onboarding.accounts_setup.domain.SetupUseCase
import com.github.emilg1101.budgeting.onboarding.di.OnboardingFlowScope
import com.github.emilg1101.budgeting.onboarding.model.CategoryModel
import com.github.emilg1101.budgeting.onboarding.model.SetupModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@OnboardingFlowScope
class AccountsSetupViewModel @Inject constructor(
    private val getDefaultAccountsUseCase: GetDefaultAccountsUseCase,
    private val setupUseCase: SetupUseCase,
    private val setupModel: SetupModel
) : BaseViewModel() {

    val accounts =
        getDefaultAccountsUseCase().mapFlatten { CategoryModel(it.emoji, it.name) }.asLiveData()

    fun onNextButtonClick() {
        setupModel.accounts = accounts.value ?: emptyList()
        viewModelScope.launch {
            setupUseCase.invoke(
                setupModel.categories.map { EmptyCategory(it.name, it.emoji) },
                setupModel.accounts.map { EmptyCategory(it.name, it.emoji) })
        }
    }
}
