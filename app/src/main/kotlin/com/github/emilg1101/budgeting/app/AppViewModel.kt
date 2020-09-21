package com.github.emilg1101.budgeting.app

import androidx.lifecycle.*
import com.github.emilg1101.budgeting.app.domain.GetCurrentUserUseCase
import com.github.emilg1101.budgeting.app.domain.GetSyncStatusUseCase
import com.github.emilg1101.budgeting.core.Event
import kotlinx.coroutines.launch
import javax.inject.Inject

class AppViewModel @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val getSyncStatusUseCase: GetSyncStatusUseCase
) : ViewModel() {

    private val _hasLoggedInUserEvent = MutableLiveData<Event<Boolean>>()
    val hasLoggedInUser: LiveData<Event<Boolean>>
        get() = _hasLoggedInUserEvent

    val syncStatus = getSyncStatusUseCase().asLiveData()

    init {
        viewModelScope.launch {
            _hasLoggedInUserEvent.value = Event(getCurrentUserUseCase())
        }
    }
}
