package com.github.emilg1101.budgeting.create_category.di

import androidx.lifecycle.ViewModel
import com.github.emilg1101.budgeting.create_category.ui.CreateCategoryViewModel
import com.github.emilg1101.budgeting.core.di.scope.FeatureScope
import com.github.emilg1101.budgeting.core.di.viewmodel.ViewModelKey
import com.github.emilg1101.budgeting.emoji_picker.OnEmojiPickerListener
import com.github.emilg1101.budgeting.emoji_picker.di.EmojiPickerDependencies
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface CreateCategoryModule {

    @Binds
    @FeatureScope
    @IntoMap
    @ViewModelKey(CreateCategoryViewModel::class)
    fun provideCreateCategoryViewModel(viewModel: CreateCategoryViewModel): ViewModel

    @Binds
    @FeatureScope
    fun bindEmojiPickerListener(viewModel: CreateCategoryViewModel): OnEmojiPickerListener

    @Binds
    @FeatureScope
    fun provideEmojiPickerComponent(component: CreateCategoryComponent): EmojiPickerDependencies
}
