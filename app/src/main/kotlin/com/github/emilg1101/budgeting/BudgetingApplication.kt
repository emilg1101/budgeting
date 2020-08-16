package com.github.emilg1101.budgeting

import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.github.emilg1101.budgeting.core.di.component.CoreComponent
import com.github.emilg1101.budgeting.core.di.component.DaggerCoreComponent

class BudgetingApplication : Application() {

    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(this)
    }

    companion object {
        @JvmStatic
        fun coreComponent(context: Context) =
            (context.applicationContext as BudgetingApplication).coreComponent
    }
}

fun Activity.coreComponent() = BudgetingApplication.coreComponent(this)
fun Fragment.coreComponent() = this.requireActivity().coreComponent()
