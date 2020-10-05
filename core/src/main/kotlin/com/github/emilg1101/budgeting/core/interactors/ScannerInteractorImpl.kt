package com.github.emilg1101.budgeting.core.interactors

import com.github.emilg1101.budgeting.scanner.api.ScannerInteractor
import com.github.emilg1101.budgeting.scanner.api.ScannerResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull

class ScannerInteractorImpl : ScannerInteractor {

    private val _scannerResult = MutableStateFlow<ScannerResult?>(null)
    override val scannerResult: Flow<ScannerResult>
        get() = _scannerResult.filterNotNull()

    override fun setScannerResult(scannerResult: ScannerResult) {
        _scannerResult.value = scannerResult
    }
}
