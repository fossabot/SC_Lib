package com.samplecode.lib

import android.os.Handler
import android.os.Looper
import kotlinx.coroutines.*

fun newThread(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    block: suspend (CoroutineScope) -> Unit,
): Job {
    return CoroutineScope(dispatcher).launch {
        block(this)
    }
}

fun newSafeThread(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    block: suspend (CoroutineScope) -> Unit,
): Job {
    return CoroutineScope(dispatcher).launch {
        try {
            block(this)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

fun postDelay(delay: Long, block: () -> Unit) {
    Handler(Looper.getMainLooper()).postDelayed({
        block()
    }, delay)
}

suspend fun CoroutineScope.sleep(ms: Long) {
    if (this.isActive) {
        withContext(Dispatchers.IO) {
            Thread.sleep(ms)
        }
    }
}