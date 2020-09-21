package com.github.emilg1101.budgeting.domain.repository

import com.github.emilg1101.budgeting.domain.entity.SyncStatus
import kotlinx.coroutines.flow.Flow

interface SyncRepository {

    val syncStatus: Flow<SyncStatus>

    suspend fun hasFiles(): Boolean

    suspend fun start()

    suspend fun restoreAllData()
}
