package com.github.emilg1101.budgeting.emojipicker

import com.github.emilg1101.budgeting.emojipicker.model.EmojiModel

interface OnEmojiPickerListener {
    fun onEmojiPicked(emoji: EmojiModel)
}
