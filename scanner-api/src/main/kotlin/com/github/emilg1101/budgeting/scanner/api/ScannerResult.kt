package com.github.emilg1101.budgeting.scanner.api

import org.threeten.bp.OffsetDateTime

data class ScannerResult(val amount: Long, val date: OffsetDateTime)
