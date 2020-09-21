package com.github.emilg1101.budgeting.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

inline fun <T, R> Flow<List<T>>.mapFlatten(crossinline transform: (value: T) -> R): Flow<List<R>> = flow {
    collect {
        emit(it.map(transform))
    }
}

fun <T> LiveData<T>.asFlow(): Flow<T> = flow {
    val channel = Channel<T>(Channel.CONFLATED)
    val observer = Observer<T> {
        channel.offer(it)
    }
    withContext(Dispatchers.Main.immediate) {
        observeForever(observer)
    }
    try {
        for (value in channel) {
            emit(value)
        }
    } finally {
        GlobalScope.launch(Dispatchers.Main.immediate) {
            removeObserver(observer)
        }
    }
}
