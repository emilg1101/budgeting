package com.github.emilg1101.budgeting.account.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.github.emilg1101.budgeting.R
import com.github.emilg1101.budgeting.account.di.DaggerCreateAccountComponent
import com.github.emilg1101.budgeting.core.base.BottomBarCovering
import com.github.emilg1101.budgeting.core.bind
import com.github.emilg1101.budgeting.core.di.viewmodel.ViewModelFactory
import com.github.emilg1101.budgeting.coreComponent
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_create_account.*
import javax.inject.Inject

class CreateAccountFragment : BottomSheetDialogFragment(), BottomBarCovering {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: CreateAccountViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerCreateAccountComponent.builder().coreComponent(this.coreComponent()).build().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createAccountName.bind(viewModel.name)
        createAccountAmount.bind(viewModel.amount)
        viewModel.isEnabled.observe(viewLifecycleOwner, Observer {
            createAccountDone.isEnabled = it
        })
        createAccountDone.setOnClickListener {
            viewModel.create()
            dismiss()
        }
    }

    override fun getTheme(): Int {
        return R.style.AppBottomSheetDialogTheme
    }
}
