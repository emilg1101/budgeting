package com.github.emilg1101.budgeting.analytics.di

import com.github.emilg1101.budgeting.analytics.AnalyticsFragment
import com.github.emilg1101.budgeting.core.di.component.CoreComponent
import com.github.emilg1101.budgeting.core.di.scope.FeatureScope
import dagger.Component

@FeatureScope
@Component(dependencies = [CoreComponent::class], modules = [AnalyticsModule::class])
interface AnalyticsComponent {

    @Component.Builder
    interface Builder {
        fun build(): AnalyticsComponent
        fun coreComponent(module: CoreComponent): Builder
    }

    fun inject(fragment: AnalyticsFragment)
}
