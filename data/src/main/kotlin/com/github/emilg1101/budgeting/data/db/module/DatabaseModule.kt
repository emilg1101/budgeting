package com.github.emilg1101.budgeting.data.db.module

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.github.emilg1101.budgeting.data.db.BudgetingDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context) =
        Room.databaseBuilder(context, BudgetingDatabase::class.java, "database")
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    db.execSQL("INSERT INTO categories (id, name, emoji, createdAt, type, visible) VALUES(1, 'Income', '💰', '2018-06-08T00:23:04.507Z', 2, 1)")
                }
            }).build()

    @Provides
    @Singleton
    fun provideCategoryDao(database: BudgetingDatabase) = database.categoryDao()

    @Provides
    @Singleton
    fun provideTransactionDao(database: BudgetingDatabase) = database.transactionDao()
}
