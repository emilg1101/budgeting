package com.github.emilg1101.budgeting.core.di.module

import android.content.Context
import android.content.SharedPreferences
import com.github.emilg1101.budgeting.data.repository.CategoryRepositoryImpl
import com.github.emilg1101.budgeting.data.repository.ReportRepositoryImpl
import com.github.emilg1101.budgeting.data.repository.TransactionRepositoryImpl
import com.github.emilg1101.budgeting.data.repository.UserRepositoryImpl
import com.github.emilg1101.budgeting.domain.repository.CategoryRepository
import com.github.emilg1101.budgeting.domain.repository.ReportRepository
import com.github.emilg1101.budgeting.domain.repository.TransactionRepository
import com.github.emilg1101.budgeting.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface CoreDataModule {

    @Binds
    fun bindCategoryRepository(repository: CategoryRepositoryImpl): CategoryRepository

    @Binds
    fun bindUserRepository(repository: UserRepositoryImpl): UserRepository

    @Binds
    fun bindTransactionRepository(repository: TransactionRepositoryImpl): TransactionRepository

    @Binds
    fun bindReportRepository(repository: ReportRepositoryImpl): ReportRepository

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideSharedPreferences(context: Context): SharedPreferences =
            context.getSharedPreferences("app", Context.MODE_PRIVATE)
    }
}
