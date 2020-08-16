package com.github.emilg1101.budgeting.home.di

import com.github.emilg1101.budgeting.core.di.component.CoreComponent
import com.github.emilg1101.budgeting.core.di.scope.FeatureScope
import com.github.emilg1101.budgeting.home.HomeFragment
import dagger.Component

@FeatureScope
@Component(dependencies = [CoreComponent::class], modules = [HomeModule::class])
interface HomeComponent {

    @Component.Builder
    interface Builder {
        fun build(): HomeComponent
        fun coreComponent(module: CoreComponent): Builder
    }

    fun inject(fragment: HomeFragment)
}
