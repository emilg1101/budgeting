package com.github.emilg1101.budgeting.wallets.accounts.di

import com.github.emilg1101.budgeting.wallets.accounts.ui.AccountsFragment
import com.github.emilg1101.budgeting.wallets.main.di.AllAccountsScope
import dagger.Subcomponent

@AllAccountsScope
@Subcomponent(modules = [AccountsModule::class])
interface AccountsSubcomponent {
    @Subcomponent.Builder
    interface Builder {
        fun build(): AccountsSubcomponent
    }

    fun inject(fragment: AccountsFragment)
}
