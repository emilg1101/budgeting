package com.github.emilg1101.budgeting.core.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.github.emilg1101.budgeting.core.Event
import com.github.emilg1101.budgeting.core.NavigationCommand

abstract class BaseViewModel : ViewModel() {

    protected val _snackbarError = MutableLiveData<Event<Int>>()
    val snackBarError: LiveData<Event<Int>> get() = _snackbarError

    private val _navigation = MutableLiveData<Event<NavigationCommand>>()
    val navigation: LiveData<Event<NavigationCommand>> = _navigation

    fun navigate(directions: NavDirections) {
        _navigation.value = Event(NavigationCommand.To(directions))
    }

    fun navigateUp() {
        _navigation.value = Event(NavigationCommand.Back)
    }
}
