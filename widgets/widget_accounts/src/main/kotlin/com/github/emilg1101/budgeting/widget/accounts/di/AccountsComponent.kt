package com.github.emilg1101.budgeting.widget.accounts.di

import com.github.emilg1101.budgeting.core.di.component.CoreComponent
import com.github.emilg1101.budgeting.core.di.scope.FeatureScope
import com.github.emilg1101.budgeting.widget.accounts.ui.AccountsWidgetViewHolder
import com.github.emilg1101.budgeting.widget.core.WidgetDependencies
import dagger.Component

@FeatureScope
@Component(
    dependencies = [CoreComponent::class, WidgetDependencies::class],
    modules = [AccountsModule::class]
)
interface AccountsComponent {

    @Component.Builder
    interface Builder {
        fun build(): AccountsComponent
        fun dependencies(dependencies: WidgetDependencies): Builder
        fun coreComponent(component: CoreComponent): Builder
    }

    fun inject(viewHolder: AccountsWidgetViewHolder)
}
