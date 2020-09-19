package com.github.emilg1101.budgeting.analytics.income.di

import com.github.emilg1101.budgeting.analytics.income.ui.IncomeFragment
import com.github.emilg1101.budgeting.analytics.main.di.AnalyticsScope
import dagger.Subcomponent

@AnalyticsScope
@Subcomponent(modules = [IncomeModule::class])
interface IncomeSubcomponent {
    @Subcomponent.Builder
    interface Builder {
        fun build(): IncomeSubcomponent
    }

    fun inject(fragment: IncomeFragment)
}
