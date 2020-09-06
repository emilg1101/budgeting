package com.github.emilg1101.budgeting.widget.balance.di

import com.github.emilg1101.budgeting.core.di.component.CoreComponent
import com.github.emilg1101.budgeting.core.di.scope.FeatureScope
import com.github.emilg1101.budgeting.widget.balance.ui.TotalBalanceViewHolder
import com.github.emilg1101.budgeting.widget.core.WidgetDependencies
import dagger.Component

@FeatureScope
@Component(
    dependencies = [CoreComponent::class, WidgetDependencies::class],
    modules = [TotalBalanceModule::class]
)
interface TotalBalanceComponent {

    @Component.Builder
    interface Builder {
        fun build(): TotalBalanceComponent
        fun dependencies(dependencies: WidgetDependencies): Builder
        fun coreComponent(component: CoreComponent): Builder
    }

    fun inject(viewHolder: TotalBalanceViewHolder)
}
