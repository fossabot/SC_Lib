package com.samplecode.lib.async

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

interface AsyncImpl {

    val jobs: MutableList<Job>

    fun newThread(
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        block: suspend (CoroutineScope) -> Unit,
    )

    fun newSafeThread(
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        block: suspend (CoroutineScope) -> Unit
    )

    fun clearJobs()
}