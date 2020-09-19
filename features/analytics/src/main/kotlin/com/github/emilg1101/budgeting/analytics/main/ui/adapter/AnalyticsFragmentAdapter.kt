package com.github.emilg1101.budgeting.analytics.main.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.github.emilg1101.budgeting.analytics.balance.ui.BalanceFragment
import com.github.emilg1101.budgeting.analytics.expenses.ui.ExpensesFragment
import com.github.emilg1101.budgeting.analytics.income.ui.IncomeFragment

class AnalyticsFragmentAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = TABS_COUNT

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> BalanceFragment()
            1 -> IncomeFragment()
            else -> ExpensesFragment()
        }
    }

    companion object {
        private const val TABS_COUNT = 3
    }
}
