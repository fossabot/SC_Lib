package com.samplecode.lib

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

fun newThread(runnable: Runnable): Job {
    return newThread(runnable, Dispatchers.IO)
}

fun newThread(runnable: Runnable, dispatcher: CoroutineContext): Job {
    return CoroutineScope(dispatcher).launch {
        runnable.run()
    }
}

fun newSafeThread(runnable: Runnable): Job {
    return newSafeThread(runnable, Dispatchers.IO)
}

fun newSafeThread(runnable: Runnable, dispatcher: CoroutineContext): Job {
    return CoroutineScope(dispatcher).launch {
        try {
            runnable.run()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

fun Job.onEnd(runnable: Runnable) {
    this.invokeOnCompletion {
        runnable.run()
    }
}