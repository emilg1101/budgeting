package com.github.emilg1101.budgeting.create_account.di

import com.github.emilg1101.budgeting.create_account.ui.CreateAccountFragment
import com.github.emilg1101.budgeting.core.di.component.CoreComponent
import com.github.emilg1101.budgeting.core.di.scope.FeatureScope
import dagger.Component

@FeatureScope
@Component(dependencies = [CoreComponent::class], modules = [CreateAccountModule::class])
interface CreateAccountComponent {

    @Component.Builder
    interface Builder {
        fun build(): CreateAccountComponent
        fun coreComponent(module: CoreComponent): Builder
    }

    fun inject(fragment: CreateAccountFragment)
}
