package com.github.emilg1101.budgeting.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.github.emilg1101.budgeting.data.db.converter.CategoryTypeConverter
import com.github.emilg1101.budgeting.data.db.converter.DateConverter
import com.github.emilg1101.budgeting.data.db.dao.CategoryDao
import com.github.emilg1101.budgeting.data.db.entity.CategoryEntity

@Database(
    entities = [
        CategoryEntity::class
    ], version = 1
)
@TypeConverters(value = [DateConverter::class, CategoryTypeConverter::class])
abstract class BudgetingDatabase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao
}
