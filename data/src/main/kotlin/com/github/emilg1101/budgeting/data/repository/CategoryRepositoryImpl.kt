package com.github.emilg1101.budgeting.data.repository

import com.github.emilg1101.budgeting.data.db.dao.CategoryDao
import com.github.emilg1101.budgeting.data.db.entity.CategoryEntity
import com.github.emilg1101.budgeting.data.db.entity.CategoryType
import com.github.emilg1101.budgeting.domain.entity.*
import com.github.emilg1101.budgeting.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.lang.IllegalArgumentException
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val categoryDao: CategoryDao
) : CategoryRepository {

    override fun getCategories(): Flow<List<Category>> {
        return categoryDao.getCategoriesByType(CategoryType.CATEGORY).map {
            it.map { entity ->
                Category(
                    entity.id,
                    entity.name,
                    0,
                    entity.createdAt,
                    entity.emoji
                )
            }
        }
    }

    override fun getAccounts(): Flow<List<Account>> {
        return categoryDao.getCategoriesByTypeWithAmount(CategoryType.ACCOUNT).map {
            it.map { entity ->
                Account(
                    entity.id,
                    entity.name,
                    entity.amount,
                    entity.createdAt,
                    entity.emoji
                )
            }
        }
    }

    override fun getIncome(): Flow<List<Income>> {
        return categoryDao.getCategoriesByTypes(listOf(CategoryType.INCOME)).map {
            it.map { entity ->
                Income(
                    entity.id,
                    entity.name,
                    0,
                    entity.createdAt,
                    entity.emoji
                )
            }
        }
    }

    override suspend fun createAccount(account: EmptyCategory) : Int {
        val entity = account.let {
            CategoryEntity(it.id, it.name, "💳", it.created, CategoryType.ACCOUNT)
        }
        return categoryDao.insert(entity).toInt()
    }

    override suspend fun createCategory(category: EmptyCategory) {
        val entity = category.let {
            CategoryEntity(it.id, it.name, it.emoji, it.created, CategoryType.CATEGORY)
        }
        categoryDao.insert(entity)
    }

    override suspend fun createAccounts(accounts: List<EmptyCategory>) {
        categoryDao.insert(accounts.map {
            CategoryEntity(
                it.id,
                it.name,
                it.emoji,
                it.created,
                CategoryType.ACCOUNT
            )
        })
    }

    override suspend fun createCategories(categories: List<EmptyCategory>) {
        categoryDao.insert(categories.map {
            CategoryEntity(
                it.id,
                it.name,
                it.emoji,
                it.created,
                CategoryType.CATEGORY
            )
        })
    }

    override fun defaultCategories(): Flow<List<EmptyCategory>> {
        return flow {
            emit(
                listOf(
                    EmptyCategory("Food", "🥑"),
                    EmptyCategory("Food", "🥑"),
                    EmptyCategory("Food", "🥑"),
                    EmptyCategory("Food", "🥑"),
                    EmptyCategory("Food", "🥑"),
                    EmptyCategory("Food", "🥑"),
                    EmptyCategory("Food", "🥑"),
                    EmptyCategory("Food", "🥑"),
                    EmptyCategory("Food", "🥑"),
                    EmptyCategory("Food", "🥑"),
                    EmptyCategory("Food", "🥑"),
                    EmptyCategory("Food", "🥑"),
                    EmptyCategory("Food", "🥑"),
                    EmptyCategory("Food", "🥑"),
                    EmptyCategory("Food", "🥑")
                )
            )
        }
    }

    override fun defaultAccounts(): Flow<List<EmptyCategory>> {
        return flow {
            emit(
                listOf(
                    EmptyCategory("Food", "🥑"),
                    EmptyCategory("Food", "🥑")
                )
            )
        }
    }

    override suspend fun findCategory(categoryId: Int): ICategory {
        val entity = categoryDao.find(categoryId)
        return when (entity?.type) {
            CategoryType.ACCOUNT -> Account(
                entity.id,
                entity.name,
               0,
                entity.createdAt,
                entity.emoji
            )
            CategoryType.CATEGORY -> Category(
                entity.id,
                entity.name,
                0,
                entity.createdAt,
                entity.emoji
            )
            CategoryType.INCOME -> Income(
                entity.id,
                entity.name,
                0,
                entity.createdAt,
                entity.emoji
            )
            else -> throw IllegalArgumentException()
        }
    }
}
