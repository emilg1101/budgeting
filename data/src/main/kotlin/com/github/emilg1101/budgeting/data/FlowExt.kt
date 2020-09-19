package com.github.emilg1101.budgeting.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

inline fun <T, R> Flow<List<T>>.mapFlatten(crossinline transform: (value: T) -> R): Flow<List<R>> = flow {
    collect {
        emit(it.map(transform))
    }
}
