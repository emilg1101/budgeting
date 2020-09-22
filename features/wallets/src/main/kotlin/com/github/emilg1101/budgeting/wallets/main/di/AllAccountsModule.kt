package com.github.emilg1101.budgeting.wallets.main.di

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import com.github.emilg1101.budgeting.core.di.scope.FeatureScope
import com.github.emilg1101.budgeting.core.di.viewmodel.ViewModelKey
import com.github.emilg1101.budgeting.wallets.main.ui.AllAccountsFragment
import com.github.emilg1101.budgeting.wallets.main.ui.AllAccountsViewModel
import com.github.emilg1101.budgeting.wallets.main.ui.adapter.AllAccountsFragmentAdapter
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
interface AllAccountsModule {

    @Binds
    @IntoMap
    @ViewModelKey(AllAccountsViewModel::class)
    fun provideAllAccountsViewModel(viewModel: AllAccountsViewModel): ViewModel

    @Module
    companion object {
        @JvmStatic
        @Provides
        fun provideFragmentManager(fragment: AllAccountsFragment) = fragment.childFragmentManager

        @JvmStatic
        @Provides
        fun provideLifecycle(fragment: AllAccountsFragment) = fragment.lifecycle

        @JvmStatic
        @FeatureScope
        @Provides
        fun provideAnalyticsFragmentAdapter(
            fragmentManager: FragmentManager,
            lifecycle: Lifecycle
        ) = AllAccountsFragmentAdapter(fragmentManager, lifecycle)
    }
}
