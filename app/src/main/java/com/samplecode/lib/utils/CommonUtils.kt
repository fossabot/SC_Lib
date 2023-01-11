package com.samplecode.lib.utils

object CommonUtils {

    @JvmStatic
    fun <T> collectionIsNullOrEmpty(collection: Collection<T>?): Boolean {
        return collection.isNullOrEmpty()
    }
}