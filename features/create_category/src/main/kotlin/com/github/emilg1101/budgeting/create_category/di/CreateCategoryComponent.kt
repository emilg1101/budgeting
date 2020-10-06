package com.github.emilg1101.budgeting.create_category.di

import com.github.emilg1101.budgeting.create_category.ui.CreateCategoryFragment
import com.github.emilg1101.budgeting.core.di.component.CoreComponent
import com.github.emilg1101.budgeting.core.di.scope.FeatureScope
import com.github.emilg1101.budgeting.emojipicker.di.EmojiPickerDependencies
import dagger.Component

@FeatureScope
@Component(dependencies = [CoreComponent::class], modules = [CreateCategoryModule::class])
interface CreateCategoryComponent: EmojiPickerDependencies {

    @Component.Builder
    interface Builder {
        fun build(): CreateCategoryComponent
        fun coreComponent(module: CoreComponent): Builder
    }

    fun inject(fragment: CreateCategoryFragment)
}
