package com.github.emilg1101.budgeting.app.di

import com.github.emilg1101.budgeting.app.AppActivity
import com.github.emilg1101.budgeting.core.di.component.CoreComponent
import com.github.emilg1101.budgeting.core.di.scope.FeatureScope
import dagger.Component

@FeatureScope
@Component(dependencies = [CoreComponent::class])
interface AppComponent {
    @Component.Builder
    interface Builder {
        fun build(): AppComponent
        fun coreComponent(component: CoreComponent): Builder
    }

    fun inject(mainActivity: AppActivity)
}
