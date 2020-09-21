package com.github.emilg1101.budgeting.data.repository

import android.util.Log
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.await
import com.github.emilg1101.budgeting.data.*
import com.github.emilg1101.budgeting.data.db.dao.CategoryDao
import com.github.emilg1101.budgeting.data.db.dao.TransactionDao
import com.github.emilg1101.budgeting.data.db.entity.CategoryEntity
import com.github.emilg1101.budgeting.data.db.entity.CategoryType
import com.github.emilg1101.budgeting.data.worker.DataSyncWorker
import com.github.emilg1101.budgeting.domain.entity.SyncStatus
import com.github.emilg1101.budgeting.domain.repository.SyncRepository
import com.google.api.services.drive.Drive
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.*
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SyncRepositoryImpl @Inject constructor(
    private val googleDrive: Drive,
    private val categoryDao: CategoryDao,
    private val transactionDao: TransactionDao,
    private val gson: Gson,
    private val workManager: WorkManager
) : SyncRepository {

    private val _currentRequestId = MutableStateFlow<UUID?>(null)
    override val syncStatus: Flow<SyncStatus> = _currentRequestId.filterNotNull().flatMapMerge { id ->
        workManager.getWorkInfoByIdLiveData(id).asFlow()
            .filter { it.state.isFinished || it.state == WorkInfo.State.RUNNING }.map {
                when (it.state) {
                    WorkInfo.State.RUNNING -> SyncStatus.RUNNING
                    else -> SyncStatus.FINISHED
                }
            }
    }

    override suspend fun hasFiles(): Boolean {
        //syncCategories()
        return false
    }

    override suspend fun start() {
        val dataSyncRequest = OneTimeWorkRequest.from(DataSyncWorker::class.java)
        _currentRequestId.value = dataSyncRequest.id
        workManager.enqueue(dataSyncRequest)
    }

    override suspend fun restoreAllData() {
        for (file in googleDrive.queryFiles().files) {
            Log.d(
                "DRIVE_LOGS",
                "Found file: %s (%s)\n".format(
                    file.name, file.id
                )
            )
        }
        val categoriesFile = googleDrive.queryFiles().files.findLast { it.name == CATEGORIES_FILE }
        categoriesFile?.id?.let { categoriesFileId ->
            val content = googleDrive.readFile(categoriesFileId).second
            val categories: List<CategoryEntity> = gson.fromJson(
                content,
                object : TypeToken<List<CategoryEntity>>() {}.type
            )
            categoryDao.insert(categories)
        }
    }

    private suspend fun syncCategories() {
        val categories = categoryDao.getCategoriesByTypes(
            listOf(
                CategoryType.INCOME,
                CategoryType.CATEGORY,
                CategoryType.ACCOUNT
            )
        ).first()
        val content = gson.toJson(categories)
        val categoriesFileId = googleDrive.createFile(CATEGORIES_FILE)
        googleDrive.saveFile(categoriesFileId, CATEGORIES_FILE, content)
    }

    private suspend fun syncTransactions() {
        val transactions = transactionDao.getAllTransactions().first()
        val content = gson.toJson(transactions)
        val transactionsFileId = googleDrive.createFile(TRANSACTIONS_FILE)
        googleDrive.saveFile(transactionsFileId, TRANSACTIONS_FILE, content)
    }

    companion object {
        private const val CATEGORIES_FILE = "categories.json"
        private const val TRANSACTIONS_FILE = "transactions.json"
    }
}
