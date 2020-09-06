package com.github.emilg1101.budgeting.widget.accounts.di

import com.github.emilg1101.budgeting.widget.core.WidgetDependencies
import com.github.emilg1101.budgeting.widget.core.WidgetInjector

object AccountsInjector : WidgetInjector<AccountsComponent> {
    lateinit var component: AccountsComponent
    override fun inject(dependencies: WidgetDependencies): AccountsComponent {
        component = DaggerAccountsComponent.builder()
            .dependencies(dependencies)
            .coreComponent(dependencies.coreComponent)
            .build()
        return component
    }
}
