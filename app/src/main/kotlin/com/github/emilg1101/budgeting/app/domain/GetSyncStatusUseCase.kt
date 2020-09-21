package com.github.emilg1101.budgeting.app.domain

import com.github.emilg1101.budgeting.domain.BaseUseCase
import com.github.emilg1101.budgeting.domain.repository.SyncRepository
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetSyncStatusUseCase @Inject constructor(
    private val syncRepository: SyncRepository
) : BaseUseCase() {
    operator fun invoke() = syncRepository.syncStatus.flowOn(ioDispatcher)
}
