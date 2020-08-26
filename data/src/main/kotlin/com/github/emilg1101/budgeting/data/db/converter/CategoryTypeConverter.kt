package com.github.emilg1101.budgeting.data.db.converter

import androidx.room.TypeConverter
import com.github.emilg1101.budgeting.data.db.entity.CategoryType

class CategoryTypeConverter {

    @TypeConverter
    fun toCategoryType(type: Int): CategoryType = when (type) {
        0 -> CategoryType.CATEGORY
        1 -> CategoryType.ACCOUNT
        else -> CategoryType.INCOME
    }

    @TypeConverter
    fun fromCategoryType(type: CategoryType): Int = type.type
}
