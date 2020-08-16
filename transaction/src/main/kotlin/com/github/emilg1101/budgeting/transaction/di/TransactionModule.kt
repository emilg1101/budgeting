package com.github.emilg1101.budgeting.transaction.di

import androidx.lifecycle.ViewModel
import com.github.emilg1101.budgeting.core.di.viewmodel.ViewModelKey
import com.github.emilg1101.budgeting.transaction.TransactionViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface TransactionModule {

    @Binds
    @IntoMap
    @ViewModelKey(TransactionViewModel::class)
    fun provideTransactionViewModel(viewModel: TransactionViewModel): ViewModel
}
