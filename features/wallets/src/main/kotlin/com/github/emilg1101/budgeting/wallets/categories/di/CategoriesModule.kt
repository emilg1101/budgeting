package com.github.emilg1101.budgeting.wallets.categories.di

import androidx.lifecycle.ViewModel
import com.github.emilg1101.budgeting.core.di.viewmodel.ViewModelKey
import com.github.emilg1101.budgeting.wallets.categories.ui.CategoriesViewModel
import com.github.emilg1101.budgeting.wallets.main.di.AllAccountsScope
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface CategoriesModule {

    @AllAccountsScope
    @Binds
    @IntoMap
    @ViewModelKey(CategoriesViewModel::class)
    fun provideCategoriesViewModel(viewModel: CategoriesViewModel): ViewModel
}
