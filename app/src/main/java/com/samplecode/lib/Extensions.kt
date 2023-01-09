package com.samplecode.lib

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import java.io.PrintWriter
import java.io.StringWriter
import java.text.SimpleDateFormat

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

inline fun <reified T : java.io.Serializable> AppCompatActivity.getSerializeExtra(
    name: String,
    data: Class<T>,
    defaultValue: T
): T {
    return this.getSerializeExtraOrNull(name, data) ?: defaultValue
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

/**
 * Форматирует [date] по [формату](https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html)
 *
 * Если во время форматирования произошла ошибка (ввод был отрицательный), тогда возвращает пустую строку
 *
 * @param date Дата и время в формате [TimeStamp](https://ru.wikipedia.org/wiki/%D0%92%D1%80%D0%B5%D0%BC%D0%B5%D0%BD%D0%BD%D0%B0%D1%8F_%D0%BC%D0%B5%D1%82%D0%BA%D0%B0)
 * @param defaultValue Возвращаемая переменная если в ходе форматирования произошла ошибка
 */
fun SimpleDateFormat.format(date: Long, defaultValue: String = ""): String {
    var str: String? = null
    try {
        str = this.format(date)
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return str ?: defaultValue
}

/**
 * Расширение для получения [String] из [Intent]
 *
 * ###Пример Kotlin:
 * ```
 * val userName: String = intent.getString(name="user_name", defaultValue="Ivan")
 * findViewById<TextView>(R.id.textView).text = "User name $userName"
 * ```
 *
 * ###Пример Java:
 * ```
 * String userName = ExtensionsKt.getString(intent, "user_name", "Ivan");
 * ((TextView) findViewById(R.id.textView)).setText("User name " + userName);
 * ```
 */
fun Intent.getString(name: String, defaultValue: String = ""): String {
    return this.getStringExtra(name) ?: defaultValue
}