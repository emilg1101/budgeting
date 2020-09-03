package com.github.emilg1101.budgeting.wallets.di

import androidx.lifecycle.ViewModel
import com.github.emilg1101.budgeting.core.di.viewmodel.ViewModelKey
import com.github.emilg1101.budgeting.wallets.WalletsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface WalletsModule {

    @Binds
    @IntoMap
    @ViewModelKey(WalletsViewModel::class)
    fun provideWalletsViewModel(viewModel: WalletsViewModel): ViewModel
}
