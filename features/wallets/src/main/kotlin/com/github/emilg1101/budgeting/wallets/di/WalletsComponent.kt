package com.github.emilg1101.budgeting.wallets.di

import com.github.emilg1101.budgeting.core.di.component.CoreComponent
import com.github.emilg1101.budgeting.core.di.scope.FeatureScope
import com.github.emilg1101.budgeting.wallets.WalletsFragment
import dagger.Component

@FeatureScope
@Component(dependencies = [CoreComponent::class], modules = [WalletsModule::class])
interface WalletsComponent {

    @Component.Builder
    interface Builder {
        fun build(): WalletsComponent
        fun coreComponent(module: CoreComponent): Builder
    }

    fun inject(fragment: WalletsFragment)
}
