package com.github.emilg1101.budgeting.widget.core

import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavController
import com.github.emilg1101.budgeting.core.di.component.CoreComponent

interface WidgetDependencies {
    val navController: NavController
    val viewModelStoreOwner: ViewModelStoreOwner
    val coreComponent: CoreComponent
}
