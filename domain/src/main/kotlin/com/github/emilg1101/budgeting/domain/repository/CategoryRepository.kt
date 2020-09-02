package com.github.emilg1101.budgeting.domain.repository

import com.github.emilg1101.budgeting.domain.entity.Account
import com.github.emilg1101.budgeting.domain.entity.Category
import com.github.emilg1101.budgeting.domain.entity.EmptyCategory
import com.github.emilg1101.budgeting.domain.entity.ICategory
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

    fun getCategories(): Flow<List<Category>>

    fun getAccounts(): Flow<List<Account>>

    suspend fun createAccount(account: EmptyCategory)

    suspend fun createCategory(category: EmptyCategory)

    suspend fun createAccounts(accounts: List<EmptyCategory>)

    suspend fun createCategories(categories: List<EmptyCategory>)

    fun defaultCategories(): Flow<List<EmptyCategory>>

    fun defaultAccounts(): Flow<List<EmptyCategory>>
}
