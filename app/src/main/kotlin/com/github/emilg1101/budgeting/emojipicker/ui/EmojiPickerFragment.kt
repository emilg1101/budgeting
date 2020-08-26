package com.github.emilg1101.budgeting.emojipicker.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.github.emilg1101.budgeting.R
import com.github.emilg1101.budgeting.core.base.BottomBarCovering
import com.github.emilg1101.budgeting.emojipicker.OnEmojiPickerListener
import com.github.emilg1101.budgeting.emojipicker.di.DaggerEmojiPickerComponent
import com.github.emilg1101.budgeting.emojipicker.di.HasEmojiPickerDependencies
import com.github.emilg1101.budgeting.emojipicker.model.EmojiModel
import com.github.emilg1101.budgeting.emojipicker.ui.adapter.EmojiAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_emoji_picker.*
import javax.inject.Inject

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
        emojiPickerList.layoutManager = GridLayoutManager(requireContext(), 5)
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
        return R.style.AppBottomSheetDialogTheme
    }
}
