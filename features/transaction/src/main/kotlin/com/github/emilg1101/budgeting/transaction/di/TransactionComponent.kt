package com.github.emilg1101.budgeting.transaction.di

import com.github.emilg1101.budgeting.core.di.component.CoreComponent
import com.github.emilg1101.budgeting.core.di.scope.FeatureScope
import com.github.emilg1101.budgeting.coreComponent
import com.github.emilg1101.budgeting.transaction.ui.TransactionFragment
import com.github.emilg1101.budgeting.transaction.ui.picker.DatePickerFragment
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
    fun inject(fragment: DatePickerFragment)

    companion object {
        lateinit var component: TransactionComponent
        fun init(fragment: TransactionFragment): TransactionComponent {
            component = DaggerTransactionComponent.builder().coreComponent(fragment.coreComponent()).build()
            return component
        }
    }
}
