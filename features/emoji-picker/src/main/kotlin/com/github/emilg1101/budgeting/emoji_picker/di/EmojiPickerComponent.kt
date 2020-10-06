package com.github.emilg1101.budgeting.emoji_picker.di

import com.github.emilg1101.budgeting.emoji_picker.ui.EmojiPickerFragment
import dagger.Component

@EmojiPickerScope
@Component(dependencies = [EmojiPickerDependencies::class])
internal interface EmojiPickerComponent {
    fun inject(fragment: EmojiPickerFragment)
}
