package com.samplecode.lib.web

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

/**
 * Отправляет POST запрос на сервер
 *
 * @param url Ссылка на запрос
 * @param body Тело запроса
 */
fun post(url: String, body: String): String {
    val client = OkHttpClient()
    val request = Request.Builder()
        .url(url)
        .post(body.toRequestBody())
        .header("Content-Type", "application/json")
        .build()

    var result = ""
    try {
        result = client.newCall(request).execute().body.string()
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return result
}

/**
 * Отправляет POST запрос на сервер
 *
 * @param url Ссылка на запрос
 * @param body Тело запроса
 * @param defaultValue Переменная если произошла ошибка при форматировании Json
 */
inline fun <reified T : Any> post(
    url: String,
    body: String,
    defaultValue: T
): T {
    val gson = Gson()
    val respond = post(url, body)
    var result: T? = null
    try {
        val type = object : TypeToken<T>() {}.type
        result = gson.fromJson(respond, type)
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return result ?: defaultValue
}

/**
 * Отправляет GET запрос на сервер
 *
 * @param url Ссылка на запрос
 */
fun get(url: String): String {
    val client = OkHttpClient()
    val request = Request.Builder()
        .url(url)
        .get()
        .build()

    var result = ""
    try {
        result = client.newCall(request).execute().body.string()
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return result
}

/**
 * Отправляет GET запрос на сервер
 *
 * @param url Ссылка на запрос
 * @param defaultValue Переменная если произошла ошибка при форматировании Json
 */
inline fun <reified T : Any> get(url: String, defaultValue: T): T {
    val gson = Gson()
    val respond = get(url)
    var result: T? = null
    try {
        val type = object : TypeToken<T>() {}.type
        result = gson.fromJson(respond, type)
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return result ?: defaultValue
}