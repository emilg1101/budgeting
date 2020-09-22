package com.github.emilg1101.budgeting.wallets.categories.di

import com.github.emilg1101.budgeting.wallets.categories.ui.CategoriesFragment
import com.github.emilg1101.budgeting.wallets.main.di.AllAccountsScope
import dagger.Subcomponent

@AllAccountsScope
@Subcomponent(modules = [CategoriesModule::class])
interface CategoriesSubcomponent {
    @Subcomponent.Builder
    interface Builder {
        fun build(): CategoriesSubcomponent
    }

    fun inject(fragment: CategoriesFragment)
}
