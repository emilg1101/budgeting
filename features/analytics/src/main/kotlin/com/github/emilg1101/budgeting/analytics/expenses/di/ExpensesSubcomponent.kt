package com.github.emilg1101.budgeting.analytics.expenses.di

import com.github.emilg1101.budgeting.analytics.expenses.ui.ExpensesFragment
import com.github.emilg1101.budgeting.analytics.main.di.AnalyticsScope
import dagger.Subcomponent

@AnalyticsScope
@Subcomponent(modules = [ExpensesModule::class])
interface ExpensesSubcomponent {
    @Subcomponent.Builder
    interface Builder {
        fun build(): ExpensesSubcomponent
    }

    fun inject(fragment: ExpensesFragment)
}
