package com.github.emilg1101.budgeting.core.di.component

import android.content.Context
import android.content.SharedPreferences
import com.github.emilg1101.budgeting.core.di.module.CoreDataModule
import com.github.emilg1101.budgeting.core.di.module.DateTimeFormatModule
import com.github.emilg1101.budgeting.core.di.qualifier.*
import com.github.emilg1101.budgeting.data.DataModule
import com.github.emilg1101.budgeting.data.db.module.DatabaseModule
import com.github.emilg1101.budgeting.core.di.worker.AppWorkerFactory
import com.github.emilg1101.budgeting.domain.repository.*
import dagger.BindsInstance
import dagger.Component
import org.threeten.bp.format.DateTimeFormatter
import javax.inject.Singleton

@Component(modules = [CoreDataModule::class, DatabaseModule::class, DataModule::class, DateTimeFormatModule::class])
@Singleton
interface CoreComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance appContext: Context): CoreComponent
    }

    fun provideContext(): Context

    fun provideSharedPreferences(): SharedPreferences

    fun provideCategoryRepository(): CategoryRepository

    fun provideUserRepository(): UserRepository

    fun provideTransactionRepository(): TransactionRepository

    fun provideReportRepository(): ReportRepository

    fun provideSyncRepository(): SyncRepository

    @MediumDateTime
    fun provideMediumDateTimeFormatter(): DateTimeFormatter

    @MediumDate
    fun provideMediumDateFormatter(): DateTimeFormatter

    @ShortDate
    fun provideShortDateFormatter(): DateTimeFormatter

    @ShortTime
    fun provideShortTimeFormatter(): DateTimeFormatter

    @ShortMonthDate
    fun provideShortMonthFormatter(): DateTimeFormatter

    fun provideWorkerFactory(): AppWorkerFactory
}
