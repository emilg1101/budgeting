package com.github.emilg1101.budgeting.wallets.accounts.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.github.emilg1101.budgeting.core.base.BaseFragment
import com.github.emilg1101.budgeting.core.di.viewmodel.ViewModelFactory
import com.github.emilg1101.budgeting.wallets.accounts.ui.adapter.AccountAdapter
import com.github.emilg1101.budgeting.wallets.main.di.AllAccountsComponent
import kotlinx.android.synthetic.main.fragment_accounts.*
import javax.inject.Inject
import com.github.emilg1101.budgeting.R as R2

class AccountsFragment :
    BaseFragment<AccountsViewModel>(com.github.emilg1101.budgeting.wallets.R.layout.fragment_accounts) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var accountAdapter: AccountAdapter

    override val viewModel: AccountsViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        AllAccountsComponent.component.accountsSubcomponent().build().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        accounts.adapter = accountAdapter
        observeViewModel()
        addAccount.setOnClickListener {
            findNavController().navigate(R2.id.createAccount)
        }
    }

    private fun observeViewModel() {
        viewModel.accounts.observe(viewLifecycleOwner, Observer {
            accountAdapter.items = it
        })
        viewModel.stubVisibility.observe(viewLifecycleOwner, Observer {
            stub.isVisible = it
        })
    }
}
