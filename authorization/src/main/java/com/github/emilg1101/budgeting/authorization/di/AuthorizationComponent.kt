package com.github.emilg1101.budgeting.authorization.di

import androidx.lifecycle.ViewModelStoreOwner
import com.github.emilg1101.budgeting.authorization.ui.AuthorizationFragment
import com.github.emilg1101.budgeting.core.di.component.CoreComponent
import com.github.emilg1101.budgeting.core.di.scope.FeatureScope
import com.github.emilg1101.budgeting.coreComponent
import dagger.BindsInstance
import dagger.Component

@FeatureScope
@Component(dependencies = [CoreComponent::class], modules = [AuthorizationModule::class])
interface AuthorizationComponent {

    @Component.Builder
    interface Builder {
        fun build(): AuthorizationComponent
        fun coreComponent(module: CoreComponent): Builder
    }

    companion object {
        lateinit var component: AuthorizationComponent
        fun init(fragment: AuthorizationFragment): AuthorizationComponent {
            component = DaggerAuthorizationComponent.builder()
                .coreComponent(fragment.coreComponent())
                .build()
            return component
        }
    }

    fun inject(fragment: AuthorizationFragment)
}
