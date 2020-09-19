package com.github.emilg1101.budgeting.data.db.entity

import androidx.room.Embedded
import androidx.room.Relation

data class TransactionAndCategoriesEntity(
    @Embedded val transactionEntity: TransactionEntity,
    @Relation(
        parentColumn = "fromId",
        entityColumn = "id"
    )
    val from: CategoryEntity,
    @Relation(
        parentColumn = "toId",
        entityColumn = "id"
    )
    val to: CategoryEntity
)
