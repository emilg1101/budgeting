package com.github.emilg1101.budgeting.home.settings.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.github.emilg1101.budgeting.core.NavigationCommand
import com.github.emilg1101.budgeting.core.base.BottomBarCovering
import com.github.emilg1101.budgeting.core.di.viewmodel.ViewModelFactory
import com.github.emilg1101.budgeting.core.onClick
import com.github.emilg1101.budgeting.coreComponent
import com.github.emilg1101.budgeting.home.R
import com.github.emilg1101.budgeting.home.settings.di.DaggerSettingsComponent
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_settings.*
import javax.inject.Inject
import com.github.emilg1101.budgeting.R as R2

class SettingsFragment : BottomSheetDialogFragment(), BottomBarCovering {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: SettingsViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerSettingsComponent.builder().coreComponent(this.coreComponent()).build().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        settingsLogOut.onClick = viewModel::onLogOutClick
        viewModel.navigation.observe(viewLifecycleOwner, Observer {
            it?.getContentIfNotHandled()?.let { command ->
                when (command) {
                    is NavigationCommand.To -> findNavController().navigate(
                        command.directions
                    )
                    is NavigationCommand.Back -> findNavController().navigateUp()
                }
            }
        })
    }

    override fun getTheme(): Int {
        return R2.style.AppBottomSheetDialogTheme
    }
}
