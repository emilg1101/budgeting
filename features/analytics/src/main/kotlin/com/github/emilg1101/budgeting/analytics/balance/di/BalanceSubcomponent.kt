package com.github.emilg1101.budgeting.analytics.balance.di

import com.github.emilg1101.budgeting.analytics.balance.ui.BalanceFragment
import com.github.emilg1101.budgeting.analytics.main.di.AnalyticsScope
import dagger.Subcomponent

@AnalyticsScope
@Subcomponent(modules = [BalanceModule::class])
interface BalanceSubcomponent {
    @Subcomponent.Builder
    interface Builder {
        fun build(): BalanceSubcomponent
    }

    fun inject(fragment: BalanceFragment)
}
