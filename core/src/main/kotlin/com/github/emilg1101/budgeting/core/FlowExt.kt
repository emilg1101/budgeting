package com.github.emilg1101.budgeting.core

import kotlinx.coroutines.flow.*

inline fun <T, R> Flow<List<T>>.mapFlatten(crossinline transform: (value: T) -> R): Flow<List<R>> =
    flow {
        collect {
            emit(it.map(transform))
        }
    }