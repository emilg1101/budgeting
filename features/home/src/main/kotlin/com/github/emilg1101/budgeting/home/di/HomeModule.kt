package com.github.emilg1101.budgeting.home.di

import androidx.lifecycle.ViewModel
import com.github.emilg1101.budgeting.core.di.viewmodel.ViewModelKey
import com.github.emilg1101.budgeting.home.ui.HomeViewModel
import com.github.emilg1101.budgeting.widget.core.WidgetDependencies
import com.github.emilg1101.budgeting.widget.core.WidgetFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
interface HomeModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun provideHomeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    fun bindWidgetDependencies(homeComponent: HomeComponent): WidgetDependencies

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun widgetFactory(dependencies: WidgetDependencies) = WidgetFactory(dependencies)
    }
}
