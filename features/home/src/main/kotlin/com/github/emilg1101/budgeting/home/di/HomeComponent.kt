package com.github.emilg1101.budgeting.home.di

import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.github.emilg1101.budgeting.core.di.component.CoreComponent
import com.github.emilg1101.budgeting.core.di.scope.FeatureScope
import com.github.emilg1101.budgeting.coreComponent
import com.github.emilg1101.budgeting.home.ui.HomeFragment
import com.github.emilg1101.budgeting.home.ui.Navigator
import com.github.emilg1101.budgeting.widget.core.WidgetDependencies
import dagger.BindsInstance
import dagger.Component

@FeatureScope
@Component(dependencies = [CoreComponent::class], modules = [HomeModule::class])
interface HomeComponent : WidgetDependencies {

    @Component.Builder
    interface Builder {
        fun build(): HomeComponent

        @BindsInstance
        fun viewModelStoreOwner(viewModelStoreOwner: ViewModelStoreOwner): Builder

        @BindsInstance
        fun navigator(navigator: Navigator): Builder

        @BindsInstance
        fun navController(navController: NavController): Builder

        fun coreComponent(component: CoreComponent): Builder
    }

    companion object {
        lateinit var component: HomeComponent
        fun init(fragment: HomeFragment): HomeComponent {
            component = DaggerHomeComponent.builder()
                .viewModelStoreOwner(fragment)
                .navigator(fragment)
                .navController(fragment.findNavController())
                .coreComponent(fragment.coreComponent())
                .build()
            return component
        }
    }

    fun inject(fragment: HomeFragment)
}
