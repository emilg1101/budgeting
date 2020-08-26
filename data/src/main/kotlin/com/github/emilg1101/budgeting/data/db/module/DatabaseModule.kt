package com.github.emilg1101.budgeting.data.db.module

import android.content.Context
import androidx.room.Room
import com.github.emilg1101.budgeting.data.db.BudgetingDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context) =
        Room.databaseBuilder(context, BudgetingDatabase::class.java, "database").build()

    @Provides
    @Singleton
    fun provideCategoryDao(database: BudgetingDatabase) = database.categoryDao()
}
