package com.github.emilg1101.budgeting.core.di.module

import com.github.emilg1101.budgeting.data.repository.CategoryRepositoryImpl
import com.github.emilg1101.budgeting.domain.repository.CategoryRepository
import dagger.Binds
import dagger.Module

@Module
interface CoreDataModule {

    @Binds
    fun bindCategoryRepository(repository: CategoryRepositoryImpl): CategoryRepository
}
