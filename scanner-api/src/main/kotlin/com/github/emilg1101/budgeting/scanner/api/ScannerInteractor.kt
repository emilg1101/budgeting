package com.github.emilg1101.budgeting.scanner.api

import kotlinx.coroutines.flow.Flow

interface ScannerInteractor {
    val scannerResult: Flow<ScannerResult>
    fun setScannerResult(scannerResult: ScannerResult)
}
