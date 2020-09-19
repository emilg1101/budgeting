package com.github.emilg1101.budgeting.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.threeten.bp.OffsetDateTime

@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val fromId: Int,
    val toId: Int,
    val amount: Long,
    val createdAt: OffsetDateTime,
    val type: TransactionType
)
