package com.github.emilg1101.budgeting.emoji_picker

import com.github.emilg1101.budgeting.emoji_picker.model.EmojiModel

interface OnEmojiPickerListener {
    fun onEmojiPicked(emoji: EmojiModel)
}
