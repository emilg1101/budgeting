package com.github.emilg1101.budgeting.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val emoji: String,
    val createdAt: Date,
    val type: CategoryType,
    val visible: Int = 1
)
