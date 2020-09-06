package com.github.emilg1101.budgeting.widget.balance.di

import com.github.emilg1101.budgeting.widget.core.WidgetDependencies
import com.github.emilg1101.budgeting.widget.core.WidgetInjector

object TotalBalanceInjector : WidgetInjector<TotalBalanceComponent> {
    lateinit var component: TotalBalanceComponent
    override fun inject(dependencies: WidgetDependencies): TotalBalanceComponent {
        component = DaggerTotalBalanceComponent.builder()
            .dependencies(dependencies)
            .coreComponent(dependencies.coreComponent)
            .build()
        return component
    }
}
