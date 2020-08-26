package com.github.emilg1101.budgeting.category.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.github.emilg1101.budgeting.R
import com.github.emilg1101.budgeting.category.di.DaggerCreateCategoryComponent
import com.github.emilg1101.budgeting.core.base.BottomBarCovering
import com.github.emilg1101.budgeting.core.bind
import com.github.emilg1101.budgeting.core.di.viewmodel.ViewModelFactory
import com.github.emilg1101.budgeting.coreComponent
import com.github.emilg1101.budgeting.emojipicker.di.EmojiPickerDependencies
import com.github.emilg1101.budgeting.emojipicker.di.HasEmojiPickerDependencies
import com.github.emilg1101.budgeting.emojipicker.ui.EmojiPickerFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_create_category.*
import javax.inject.Inject

class CreateCategoryFragment : BottomSheetDialogFragment(), BottomBarCovering, HasEmojiPickerDependencies {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    override lateinit var dependencies: EmojiPickerDependencies

    private val viewModel: CreateCategoryViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerCreateCategoryComponent.builder().coreComponent(this.coreComponent()).build()
            .inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createCategoryName.bind(viewModel.name)
        viewModel.selectedEmoji.observe(viewLifecycleOwner, Observer {
            createCategoryEmoji.text = it.emoji
        })
        viewModel.isEnabled.observe(viewLifecycleOwner, Observer {
            createCategoryDone.isEnabled = it
        })
        createCategoryEmoji.setOnClickListener { EmojiPickerFragment().show(childFragmentManager, "") }
        createCategoryDone.setOnClickListener { viewModel.create() }
    }

    override fun getTheme(): Int {
        return R.style.AppBottomSheetDialogTheme
    }
}
