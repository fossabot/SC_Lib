package com.samplecode.lib

import android.os.Handler
import android.os.Looper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

fun postDelay(delay: Long, block: () -> Unit) {
    Handler(Looper.getMainLooper()).postDelayed({
        block()
    }, delay)
}

suspend fun sleep(ms: Long) {
    withContext(Dispatchers.IO) {
        Thread.sleep(ms)
    }
}