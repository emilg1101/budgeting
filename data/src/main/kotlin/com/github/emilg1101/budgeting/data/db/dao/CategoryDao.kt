package com.github.emilg1101.budgeting.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.emilg1101.budgeting.data.db.entity.CategoryEntity
import com.github.emilg1101.budgeting.data.db.entity.CategoryType
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Query("SELECT * FROM categories WHERE type == :type")
    fun getCategoriesByType(type: CategoryType): Flow<List<CategoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: CategoryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entities: List<CategoryEntity>)
}
