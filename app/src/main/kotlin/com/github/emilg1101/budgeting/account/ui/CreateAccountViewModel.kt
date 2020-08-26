package com.github.emilg1101.budgeting.account.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.github.emilg1101.budgeting.account.domain.CreateAccountUseCase
import com.github.emilg1101.budgeting.core.base.BaseViewModel
import com.github.emilg1101.budgeting.core.di.scope.FeatureScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@FeatureScope
class CreateAccountViewModel @Inject constructor(
    private val createAccountUseCase: CreateAccountUseCase
) : BaseViewModel() {

    val name = MutableLiveData<String>()
    val amount = MutableLiveData<String>()

    private val _isEnabled = MediatorLiveData<Boolean>().apply {
        this.value = false
        addSource(name) { this.value = validate(it) }
        addSource(amount) { this.value = validate(name.value) }
    }
    val isEnabled: LiveData<Boolean>
        get() = _isEnabled

    fun create() {
        viewModelScope.launch {
            name.value?.let { name ->
                createAccountUseCase(
                    CreateAccountUseCase.Params(
                        name,
                        amount.value?.toFloatOrNull()?.times(100)?.toLong() ?: 0L
                    )
                )
                navigateUp()
            }
        }
    }

    private fun validate(name: String?): Boolean {
        return !name.isNullOrEmpty()
    }
}
