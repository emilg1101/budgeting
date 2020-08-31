package com.github.emilg1101.budgeting.home.settings.ui

import androidx.lifecycle.viewModelScope
import androidx.navigation.ActionOnlyNavDirections
import com.github.emilg1101.budgeting.core.base.BaseViewModel
import com.github.emilg1101.budgeting.core.di.scope.FeatureScope
import com.github.emilg1101.budgeting.emojipicker.domain.LogOutUseCase
import com.github.emilg1101.budgeting.R as R2
import kotlinx.coroutines.launch
import javax.inject.Inject

@FeatureScope
class SettingsViewModel @Inject constructor(
    private val logOutUseCase: LogOutUseCase
) : BaseViewModel() {

    fun onLogOutClick() {
        viewModelScope.launch {
            logOutUseCase()
            navigate(ActionOnlyNavDirections(R2.id.action_settings_to_authorization))
        }
    }
}
