package com.github.emilg1101.budgeting.wallets.main.di

import com.github.emilg1101.budgeting.core.di.component.CoreComponent
import com.github.emilg1101.budgeting.core.di.scope.FeatureScope
import com.github.emilg1101.budgeting.coreComponent
import com.github.emilg1101.budgeting.wallets.accounts.di.AccountsSubcomponent
import com.github.emilg1101.budgeting.wallets.categories.di.CategoriesSubcomponent
import com.github.emilg1101.budgeting.wallets.main.ui.AllAccountsFragment
import dagger.BindsInstance
import dagger.Component

@FeatureScope
@Component(dependencies = [CoreComponent::class], modules = [AllAccountsModule::class])
interface AllAccountsComponent {

    @Component.Builder
    interface Builder {
        fun build(): AllAccountsComponent

        @BindsInstance
        fun bindFragment(fragment: AllAccountsFragment): Builder
        fun coreComponent(module: CoreComponent): Builder
    }

    companion object {
        lateinit var component: AllAccountsComponent
        fun init(fragment: AllAccountsFragment): AllAccountsComponent {
            component = DaggerAllAccountsComponent.builder()
                .bindFragment(fragment)
                .coreComponent(fragment.coreComponent())
                .build()
            return component
        }
    }

    fun accountsSubcomponent(): AccountsSubcomponent.Builder
    fun categoriesSubcomponent(): CategoriesSubcomponent.Builder

    fun inject(fragment: AllAccountsFragment)
}
