package com.samplecode.lib.async.kotlin

import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

abstract class AsyncActivity : AppCompatActivity(), AsyncImpl {

    override val jobs: MutableList<Job> = mutableListOf()

    override fun newThread(
        dispatcher: CoroutineDispatcher,
        block: suspend (CoroutineScope) -> Unit,
    ) {
        val job = CoroutineScope(dispatcher).launch {
            block(this)
        }
        job.invokeOnCompletion {
            jobs.remove(job)
        }
        jobs.add(job)
    }

    override fun newSafeThread(
        dispatcher: CoroutineDispatcher,
        block: suspend (CoroutineScope) -> Unit,
    ) {
        val job = CoroutineScope(dispatcher).launch {
            try {
                block(this)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        job.invokeOnCompletion {
            jobs.remove(job)
        }
        jobs.add(job)
    }

    override fun clearJobs() {
        if (jobs.isEmpty()) {
            return
        }
        jobs.forEach {
            try {
                it.cancel()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onDestroy() {
        clearJobs()
        super.onDestroy()
    }
}