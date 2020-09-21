package com.github.emilg1101.budgeting.core.di.module

import android.content.Context
import android.content.SharedPreferences
import com.github.emilg1101.budgeting.core.di.worker.WorkerKey
import com.github.emilg1101.budgeting.data.repository.*
import com.github.emilg1101.budgeting.data.worker.ChildWorkerFactory
import com.github.emilg1101.budgeting.data.worker.DataSyncWorker
import com.github.emilg1101.budgeting.domain.repository.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

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

    @Binds
    fun bindSyncRepository(repository: SyncRepositoryImpl): SyncRepository

    @Binds
    @IntoMap
    @WorkerKey(DataSyncWorker::class)
    fun bindDataSyncWorker(factory: DataSyncWorker.Factory): ChildWorkerFactory

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideSharedPreferences(context: Context): SharedPreferences =
            context.getSharedPreferences("app", Context.MODE_PRIVATE)
    }
}
