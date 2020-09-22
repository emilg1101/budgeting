package com.github.emilg1101.budgeting.wallets.main.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.github.emilg1101.budgeting.wallets.accounts.ui.AccountsFragment
import com.github.emilg1101.budgeting.wallets.categories.ui.CategoriesFragment

class AllAccountsFragmentAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = TABS_COUNT

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) {
            AccountsFragment()
        } else {
            CategoriesFragment()
        }
    }

    companion object {
        private const val TABS_COUNT = 2
    }
}
