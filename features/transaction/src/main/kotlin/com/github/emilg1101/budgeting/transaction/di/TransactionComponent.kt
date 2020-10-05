package com.github.emilg1101.budgeting.transaction.di

import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.github.emilg1101.budgeting.core.di.component.CoreComponent
import com.github.emilg1101.budgeting.coreComponent
import com.github.emilg1101.budgeting.transaction.ui.TransactionFragment
import com.github.emilg1101.budgeting.transaction.ui.picker.DatePickerFragment
import dagger.BindsInstance
import dagger.Component

@TransactionScope
@Component(dependencies = [CoreComponent::class], modules = [TransactionModule::class])
interface TransactionComponent {

    @Component.Builder
    interface Builder {
        fun build(): TransactionComponent
        @BindsInstance
        fun navController(navController: NavController): Builder
        fun coreComponent(module: CoreComponent): Builder
    }

    fun inject(fragment: TransactionFragment)
    fun inject(fragment: DatePickerFragment)

    companion object {
        var component: TransactionComponent? = null
        fun init(fragment: TransactionFragment): TransactionComponent? {
            component = DaggerTransactionComponent.builder().navController(fragment.findNavController()).coreComponent(fragment.coreComponent()).build()
            return component
        }
    }
}
