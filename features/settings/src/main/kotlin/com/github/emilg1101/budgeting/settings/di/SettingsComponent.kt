package com.github.emilg1101.budgeting.settings.di

import com.github.emilg1101.budgeting.core.di.component.CoreComponent
import com.github.emilg1101.budgeting.core.di.scope.FeatureScope
import com.github.emilg1101.budgeting.settings.ui.SettingsFragment
import dagger.Component

@FeatureScope
@Component(dependencies = [CoreComponent::class], modules = [SettingsModule::class])
interface SettingsComponent {
    fun inject(fragment: SettingsFragment)
}
