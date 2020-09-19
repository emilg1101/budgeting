package com.github.emilg1101.budgeting.data.db.converter

import androidx.room.TypeConverter
import com.github.emilg1101.budgeting.data.db.entity.TransactionType

class TransactionTypeConverter {

    @TypeConverter
    fun toTransactionType(type: Int): TransactionType = when (type) {
        0 -> TransactionType.EXPENSE
        1 -> TransactionType.TRANSFER
        else -> TransactionType.INCOME
    }

    @TypeConverter
    fun fromTransactionType(type: TransactionType): Int = type.type
}
