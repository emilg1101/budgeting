package com.github.emilg1101.budgeting.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.emilg1101.budgeting.data.db.entity.CategoryEntity
import com.github.emilg1101.budgeting.data.db.entity.CategoryEntityWithAmount
import com.github.emilg1101.budgeting.data.db.entity.CategoryType
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Query("SELECT * FROM categories WHERE type == :type")
    fun getCategoriesByType(type: CategoryType): Flow<List<CategoryEntity>>

    @Query("SELECT * FROM categories WHERE type IN (:types)")
    fun getCategoriesByTypes(types: List<CategoryType>): Flow<List<CategoryEntity>>

    @Query("SELECT *, (SELECT sum(t.amount) FROM transactions AS t WHERE t.fromId == ct.id OR t.toId == ct.id) AS amount FROM categories AS ct WHERE ct.type == :type")
    fun getCategoriesByTypeWithAmount(type: CategoryType): Flow<List<CategoryEntityWithAmount>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: CategoryEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entities: List<CategoryEntity>)

    @Query("SELECT * FROM categories where id == :categoryId")
    fun find(categoryId: Int): CategoryEntity?
}
