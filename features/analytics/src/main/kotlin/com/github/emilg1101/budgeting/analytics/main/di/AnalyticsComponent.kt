package com.github.emilg1101.budgeting.analytics.main.di

import com.github.emilg1101.budgeting.analytics.balance.di.BalanceSubcomponent
import com.github.emilg1101.budgeting.analytics.expenses.di.ExpensesSubcomponent
import com.github.emilg1101.budgeting.analytics.income.di.IncomeSubcomponent
import com.github.emilg1101.budgeting.analytics.main.ui.AnalyticsFragment
import com.github.emilg1101.budgeting.core.di.component.CoreComponent
import com.github.emilg1101.budgeting.core.di.scope.FeatureScope
import com.github.emilg1101.budgeting.coreComponent
import dagger.BindsInstance
import dagger.Component

@FeatureScope
@Component(dependencies = [CoreComponent::class], modules = [AnalyticsModule::class])
interface AnalyticsComponent {

    @Component.Builder
    interface Builder {
        fun build(): AnalyticsComponent
        @BindsInstance
        fun bindFragment(fragment: AnalyticsFragment): Builder
        fun coreComponent(module: CoreComponent): Builder
    }

    companion object {
        lateinit var component: AnalyticsComponent
        fun init(fragment: AnalyticsFragment): AnalyticsComponent {
            component = DaggerAnalyticsComponent.builder()
                .bindFragment(fragment)
                .coreComponent(fragment.coreComponent())
                .build()
            return component
        }
    }

    fun balanceSubcomponent(): BalanceSubcomponent.Builder
    fun incomeSubcomponent(): IncomeSubcomponent.Builder
    fun expensesSubcomponent(): ExpensesSubcomponent.Builder

    fun inject(fragment: AnalyticsFragment)
}
