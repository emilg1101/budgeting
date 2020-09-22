package com.github.emilg1101.budgeting.wallets.categories.ui

import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import com.github.emilg1101.budgeting.core.base.BaseViewModel
import com.github.emilg1101.budgeting.wallets.categories.domain.GetCategoriesUseCase
import javax.inject.Inject

class CategoriesViewModel @Inject constructor(
    getCategoriesUseCase: GetCategoriesUseCase
) : BaseViewModel() {

    val categories = getCategoriesUseCase().asLiveData()
    val stubVisibility = categories.map { it.isNullOrEmpty() }
}
