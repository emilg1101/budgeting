package com.github.emilg1101.budgeting.domain.repository

import com.github.emilg1101.budgeting.domain.entity.User

interface UserRepository {
    suspend fun getUser(): User?
}
