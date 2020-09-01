package com.github.emilg1101.budgeting.app

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.emilg1101.budgeting.app.domain.GetCurrentUserUseCase
import com.github.emilg1101.budgeting.core.Event
import kotlinx.coroutines.launch
import javax.inject.Inject

class AppViewModel @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : ViewModel() {

    private val _hasLoggedInUserEvent = MutableLiveData<Event<Boolean>>()
    val hasLoggedInUser: LiveData<Event<Boolean>>
        get() = _hasLoggedInUserEvent

    init {
        viewModelScope.launch {
            _hasLoggedInUserEvent.value = Event(getCurrentUserUseCase())
        }
    }
}
