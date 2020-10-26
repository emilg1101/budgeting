package com.github.emilg1101.budgeting.emoji_picker.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.emilg1101.budgeting.emoji_picker.R
import com.github.emilg1101.budgeting.core.base.BottomBarCovering
import com.github.emilg1101.budgeting.emoji_picker.OnEmojiPickerListener
import com.github.emilg1101.budgeting.emoji_picker.di.DaggerEmojiPickerComponent
import com.github.emilg1101.budgeting.emoji_picker.di.HasEmojiPickerDependencies
import com.github.emilg1101.budgeting.emoji_picker.model.EmojiModel
import com.github.emilg1101.budgeting.emoji_picker.ui.adapter.EmojiAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_emoji_picker.*
import javax.inject.Inject
import com.github.emilg1101.budgeting.core.R as R2

class EmojiPickerFragment : BottomSheetDialogFragment(), BottomBarCovering {

    @Inject
    lateinit var onEmojiPickerListener: OnEmojiPickerListener

    private var adapter = EmojiAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerEmojiPickerComponent.builder().emojiPickerDependencies((parentFragment as HasEmojiPickerDependencies).dependencies).build().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_emoji_picker, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        emojiPickerList.adapter = adapter
        adapter.onEmojiPickerListener = object : OnEmojiPickerListener {
            override fun onEmojiPicked(emoji: EmojiModel) {
                onEmojiPickerListener.onEmojiPicked(emoji)
                dismiss()
            }
        }
        adapter.items = listOf(
            EmojiModel("\uD83D\uDE02"),
            EmojiModel("\uD83D\uDC4D"),
            EmojiModel("\uD83D\uDE0D"),
            EmojiModel("\uD83D\uDE02"),
            EmojiModel("\uD83D\uDE02"),
            EmojiModel("\uD83D\uDE02"),
            EmojiModel("\uD83D\uDE02"),
            EmojiModel("\uD83D\uDC4D"),
            EmojiModel("\uD83D\uDE0D"),
            EmojiModel("\uD83D\uDE02"),
            EmojiModel("\uD83D\uDE02"),
            EmojiModel("\uD83D\uDE02"),
            EmojiModel("\uD83D\uDE02"),
            EmojiModel("\uD83D\uDC4D"),
            EmojiModel("\uD83D\uDE0D"),
            EmojiModel("\uD83D\uDE02"),
            EmojiModel("\uD83D\uDE02"),
            EmojiModel("\uD83D\uDE02"),
            EmojiModel("\uD83D\uDE02"),
            EmojiModel("\uD83D\uDC4D"),
            EmojiModel("\uD83D\uDE0D"),
            EmojiModel("\uD83D\uDE02"),
            EmojiModel("\uD83D\uDE02"),
            EmojiModel("\uD83D\uDE02"),
            EmojiModel("\uD83D\uDE02"),
            EmojiModel("\uD83D\uDC4D"),
            EmojiModel("\uD83D\uDE0D"),
            EmojiModel("\uD83D\uDE02"),
            EmojiModel("\uD83D\uDE02"),
            EmojiModel("\uD83D\uDE02"),
            EmojiModel("\uD83D\uDE0D"),
            EmojiModel("\uD83D\uDE02"),
            EmojiModel("\uD83D\uDE02"),
            EmojiModel("\uD83D\uDE02"),
            EmojiModel("\uD83D\uDE02"),
            EmojiModel("\uD83D\uDC4D"),
            EmojiModel("\uD83D\uDE0D"),
            EmojiModel("\uD83D\uDE02"),
            EmojiModel("\uD83D\uDE02"),
            EmojiModel("\uD83D\uDE02"),
            EmojiModel("\uD83D\uDE02"),
            EmojiModel("\uD83D\uDC4D"),
            EmojiModel("\uD83D\uDE0D"),
            EmojiModel("\uD83D\uDE02"),
            EmojiModel("\uD83D\uDE02"),
            EmojiModel("\uD83D\uDE02"),
            EmojiModel("\uD83D\uDE0D"),
            EmojiModel("\uD83D\uDE02"),
            EmojiModel("\uD83D\uDE02"),
            EmojiModel("\uD83D\uDE02"),
            EmojiModel("\uD83D\uDE02"),
            EmojiModel("\uD83D\uDC4D"),
            EmojiModel("\uD83D\uDE0D"),
            EmojiModel("\uD83D\uDE02"),
            EmojiModel("\uD83D\uDE02"),
            EmojiModel("\uD83D\uDE02"),
            EmojiModel("\uD83D\uDE02"),
            EmojiModel("\uD83D\uDC4D"),
            EmojiModel("\uD83D\uDE0D"),
            EmojiModel("\uD83D\uDE02"),
            EmojiModel("\uD83D\uDE02")
        )
    }

    override fun getTheme(): Int {
        return R2.style.AppBottomSheetDialogTheme
    }
}
