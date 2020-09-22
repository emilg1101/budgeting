package com.github.emilg1101.budgeting.wallets.main.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.updatePadding
import androidx.fragment.app.viewModels
import com.github.emilg1101.budgeting.core.base.BaseFragment
import com.github.emilg1101.budgeting.core.di.viewmodel.ViewModelFactory
import com.github.emilg1101.budgeting.wallets.R
import com.github.emilg1101.budgeting.wallets.main.di.AllAccountsComponent
import com.github.emilg1101.budgeting.wallets.main.ui.adapter.AllAccountsFragmentAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_all_accounts.*
import javax.inject.Inject

class AllAccountsFragment : BaseFragment<AllAccountsViewModel>(R.layout.fragment_all_accounts) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var allAccountsFragmentAdapter: AllAccountsFragmentAdapter

    override val viewModel: AllAccountsViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        AllAccountsComponent.init(this).inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPager()
        view.setOnApplyWindowInsetsListener { v, insets ->
            v.updatePadding(top = insets.systemWindowInsetTop)
            insets
        }
    }

    private fun setupViewPager() {
        accountsViewPager.adapter = allAccountsFragmentAdapter
        TabLayoutMediator(accountsTabs, accountsViewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "ACCOUNTS"
                }
                1 -> {
                    tab.text = "CATEGORIES"
                }
            }
        }.attach()
    }
}
