package com.github.emilg1101.budgeting.domain.repository

import com.github.emilg1101.budgeting.domain.entity.*
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

    fun getCategories(): Flow<List<Category>>

    fun getAccounts(): Flow<List<Account>>

    fun getIncome(): Flow<List<Income>>

    suspend fun createAccount(account: EmptyCategory): Int

    suspend fun createCategory(category: EmptyCategory)

    suspend fun createAccounts(accounts: List<EmptyCategory>)

    suspend fun createCategories(categories: List<EmptyCategory>)

    fun defaultCategories(): Flow<List<EmptyCategory>>

    fun defaultAccounts(): Flow<List<EmptyCategory>>

    suspend fun findCategory(categoryId: Int): ICategory
}
