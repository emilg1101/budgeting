package com.github.emilg1101.budgeting.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.github.emilg1101.budgeting.data.db.converter.CategoryTypeConverter
import com.github.emilg1101.budgeting.data.db.converter.DateConverter
import com.github.emilg1101.budgeting.data.db.converter.TransactionTypeConverter
import com.github.emilg1101.budgeting.data.db.dao.CategoryDao
import com.github.emilg1101.budgeting.data.db.dao.TransactionDao
import com.github.emilg1101.budgeting.data.db.entity.CategoryEntity
import com.github.emilg1101.budgeting.data.db.entity.TransactionEntity

@Database(
    entities = [
        CategoryEntity::class,
        TransactionEntity::class
    ], version = 1
)
@TypeConverters(value = [DateConverter::class, CategoryTypeConverter::class, TransactionTypeConverter::class])
abstract class BudgetingDatabase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao

    abstract fun transactionDao(): TransactionDao
}
