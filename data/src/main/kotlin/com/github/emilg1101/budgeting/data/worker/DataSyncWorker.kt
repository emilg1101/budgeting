package com.github.emilg1101.budgeting.data.worker

import android.content.Context
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.github.emilg1101.budgeting.domain.repository.SyncRepository
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class DataSyncWorker @Inject constructor(
    context: Context,
    params: WorkerParameters,
    private val syncRepository: SyncRepository
) : Worker(context, params), CoroutineScope {

    private val parentJob = SupervisorJob()
    private val ioDispatcher = Dispatchers.IO

    override val coroutineContext: CoroutineContext
        get() = parentJob + ioDispatcher

    override fun doWork(): Result {
        setProgressAsync(Data.Builder().putBoolean("c", false).build())
        runBlocking {
            setProgressAsync(Data.Builder().putBoolean("c", true).build())
            syncRepository.restoreAllData()
            setProgressAsync(Data.Builder().putBoolean("c", false).build())
        }
        setProgressAsync(Data.Builder().putBoolean("c", true).build())
        return Result.success(Data.Builder().putBoolean("c", true).build())
    }

    class Factory @Inject constructor(
        private val syncRepository: SyncRepository
    ) : ChildWorkerFactory {

        override fun create(appContext: Context, params: WorkerParameters): Worker {
            return DataSyncWorker(appContext, params, syncRepository)
        }
    }
}
