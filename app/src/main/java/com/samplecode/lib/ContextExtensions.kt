package com.samplecode.lib

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

fun Context?.showToast(
    message: String,
    autoConfigLength: Boolean = true,
    duration: Int = Toast.LENGTH_SHORT
) {
    if (this == null) {
        return
    }
    val length = if (autoConfigLength) {
        if (message.length > 45) {
            Toast.LENGTH_LONG
        } else {
            Toast.LENGTH_SHORT
        }
    } else {
        duration
    }
    Toast.makeText(this, message, length).apply {
        show()
    }
}

fun Context?.showToast(
    @StringRes id: Int,
    autoConfigLength: Boolean = true,
    duration: Int = Toast.LENGTH_SHORT
): Unit = this.showToast(this?.getString(id) ?: "", autoConfigLength, duration)

fun Fragment?.showToast(
    @StringRes id: Int,
    autoConfigLength: Boolean = true,
    duration: Int = Toast.LENGTH_SHORT
): Unit = this?.context.showToast(id, autoConfigLength, duration)

fun Fragment?.showToast(
    message: String,
    autoConfigLength: Boolean = true,
    duration: Int = Toast.LENGTH_SHORT
): Unit = this?.context.showToast(message, autoConfigLength, duration)