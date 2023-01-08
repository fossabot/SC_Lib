package com.samplecode.lib.states

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class FlowState<T : Any>(
    private val flow: Flow<T>,
    private val listener: FlowStateImpl<T>
) {
    init {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                flow.collect {
                    listener.onSuccess(it)
                }
            } catch (e: Exception) {
                listener.onFailure(e)
            }
        }
    }
}

interface FlowStateImpl<T : Any> {

    fun onSuccess(data: T)

    fun onFailure(e: Exception)
}

fun <T : Any> Flow<T>.toState(listener: FlowStateImpl<T>): FlowState<T> {
    return FlowState(this, listener)
}