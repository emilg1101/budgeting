package com.github.emilg1101.budgeting.home.settings.ui

import androidx.lifecycle.viewModelScope
import androidx.navigation.ActionOnlyNavDirections
import com.github.emilg1101.budgeting.core.base.BaseViewModel
import com.github.emilg1101.budgeting.core.di.scope.FeatureScope
import com.github.emilg1101.budgeting.domain.repository.SyncRepository
import com.github.emilg1101.budgeting.home.settings.domain.LogOutUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.github.emilg1101.budgeting.R as R2

@FeatureScope
class SettingsViewModel @Inject constructor(
    private val logOutUseCase: LogOutUseCase,
    private val syncRepository: SyncRepository
) : BaseViewModel() {

    fun onSyncClick() {
        viewModelScope.launch { syncRepository.start() }
    }

    fun onLogOutClick() {
        viewModelScope.launch {
            logOutUseCase()
            navigate(ActionOnlyNavDirections(R2.id.action_settings_to_authorization))
        }
    }
}
