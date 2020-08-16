package com.github.emilg1101.budgeting.transaction.di

import com.github.emilg1101.budgeting.core.di.component.CoreComponent
import com.github.emilg1101.budgeting.core.di.scope.FeatureScope
import com.github.emilg1101.budgeting.transaction.TransactionFragment
import dagger.Component

@FeatureScope
@Component(dependencies = [CoreComponent::class], modules = [TransactionModule::class])
interface TransactionComponent {

    @Component.Builder
    interface Builder {
        fun build(): TransactionComponent
        fun coreComponent(module: CoreComponent): Builder
    }

    fun inject(fragment: TransactionFragment)
}
