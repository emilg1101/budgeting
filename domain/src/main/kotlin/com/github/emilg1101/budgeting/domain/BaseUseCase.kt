package com.github.emilg1101.budgeting.domain

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

abstract class BaseUseCase : CoroutineScope {

    private val parentJob = SupervisorJob()
    //val mainDispatcher = Dispatchers.Main
    val ioDispatcher = Dispatchers.IO

    override val coroutineContext: CoroutineContext
        get() = parentJob + ioDispatcher
}
