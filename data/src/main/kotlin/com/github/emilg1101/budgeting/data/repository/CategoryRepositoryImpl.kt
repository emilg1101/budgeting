package com.github.emilg1101.budgeting.data.repository

import com.github.emilg1101.budgeting.data.db.dao.CategoryDao
import com.github.emilg1101.budgeting.data.db.entity.CategoryEntity
import com.github.emilg1101.budgeting.data.db.entity.CategoryType
import com.github.emilg1101.budgeting.domain.entity.Account
import com.github.emilg1101.budgeting.domain.entity.Category
import com.github.emilg1101.budgeting.domain.entity.EmptyCategory
import com.github.emilg1101.budgeting.domain.entity.ICategory
import com.github.emilg1101.budgeting.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*
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
                    0L,
                    entity.createdAt,
                    entity.emoji
                )
            }
        }
    }

    override fun getAccounts(): Flow<List<Account>> {
        return categoryDao.getCategoriesByType(CategoryType.ACCOUNT).map {
            it.map { entity ->
                Account(
                    entity.id,
                    entity.name,
                    0L,
                    entity.createdAt
                )
            }
        }
    }

    override suspend fun addCategory(iCategory: ICategory) {
        val entity = CategoryEntity(0, iCategory.name, "", iCategory.created, CategoryType.CATEGORY)
        categoryDao.insert(entity)
    }

    override suspend fun createAccount(account: EmptyCategory) {
        val entity = account.let {
            CategoryEntity(it.id, it.name, "", it.created, CategoryType.ACCOUNT)
        }
        categoryDao.insert(entity)
    }

    override suspend fun createCategory(category: EmptyCategory) {
        val entity = category.let {
            CategoryEntity(it.id, it.name, it.emoji, it.created, CategoryType.CATEGORY)
        }
        categoryDao.insert(entity)
    }
}
