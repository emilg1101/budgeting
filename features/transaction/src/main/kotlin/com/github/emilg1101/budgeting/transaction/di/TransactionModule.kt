package com.github.emilg1101.budgeting.transaction.di

import androidx.lifecycle.ViewModel
import com.github.emilg1101.budgeting.core.di.scope.FeatureScope
import com.github.emilg1101.budgeting.core.di.viewmodel.ViewModelKey
import com.github.emilg1101.budgeting.transaction.ui.TransactionViewModel
import com.github.emilg1101.budgeting.transaction.ui.adapter.CategoryAdapter
import com.github.emilg1101.budgeting.transaction.ui.picker.DateTimePickerCallback
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Named

@Module
interface TransactionModule {

    @Binds
    @FeatureScope
    @IntoMap
    @ViewModelKey(TransactionViewModel::class)
    fun provideTransactionViewModel(viewModel: TransactionViewModel): ViewModel

    @Binds
    @FeatureScope
    fun bindDateTimePickerCallback(viewModel: TransactionViewModel): DateTimePickerCallback

    @Module
    companion object {

        @JvmStatic
        @Provides
        @Named("Withdraw")
        fun provideWithdrawAdapter(): CategoryAdapter = CategoryAdapter()

        @JvmStatic
        @Provides
        @Named("Enrollment")
        fun provideEnrollmentAdapter(): CategoryAdapter = CategoryAdapter()
    }
}
