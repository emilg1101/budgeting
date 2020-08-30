package com.github.emilg1101.budgeting.domain.repository

import com.github.emilg1101.budgeting.domain.entity.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getUser(): User

    fun auth(idToken: String): Flow<User>

    suspend fun logout()
}
