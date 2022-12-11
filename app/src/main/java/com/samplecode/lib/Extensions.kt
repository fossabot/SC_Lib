package com.samplecode.lib

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import java.io.PrintWriter
import java.io.StringWriter

@Suppress("DEPRECATION")
inline fun <reified T : java.io.Serializable> AppCompatActivity.getSerializeExtraOrNull(
    name: String,
    data: Class<T>
): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        this.intent.getSerializableExtra(name, data)
    } else {
        this.intent.getSerializableExtra(name).checkCast()
    }
}

inline fun <reified T> Any?.checkCast(): T? {
    if (this == null) {
        return null
    }
    return if (this is T) {
        this
    } else {
        null
    }
}

fun Exception.getString(): String {
    val writer = StringWriter()
    this.printStackTrace(PrintWriter(writer))
    return writer.toString()
}