package com.github.emilg1101.budgeting.data.repository

import com.github.emilg1101.budgeting.domain.entity.User
import com.github.emilg1101.budgeting.domain.repository.UserRepository

class UserRepositoryImpl : UserRepository {
    override suspend fun getUser(): User? {
        return null
    }
}
