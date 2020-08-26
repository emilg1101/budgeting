package com.github.emilg1101.budgeting.emojipicker.di

import com.github.emilg1101.budgeting.emojipicker.ui.EmojiPickerFragment
import dagger.Component

@EmojiPickerScope
@Component(dependencies = [EmojiPickerDependencies::class])
interface EmojiPickerComponent {
    fun inject(fragment: EmojiPickerFragment)
}
