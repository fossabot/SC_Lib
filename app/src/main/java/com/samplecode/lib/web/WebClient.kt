@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package com.samplecode.lib.web

import android.util.Base64
import com.google.gson.Gson
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.lang.reflect.Type
import java.nio.charset.StandardCharsets

class WebClient {

    companion object {

        @JvmField
        var JSON = "application/json; charset=utf-8".toMediaType()

        @Volatile
        private var INSTANCE: WebClient? = null

        @JvmStatic
        fun getInstance(): WebClient {
            return INSTANCE ?: synchronized(this) {
                INSTANCE = WebClient()
                INSTANCE!!
            }
        }
    }

    /**
     * Отправляет POST запрос к серверу
     *
     * @param url Ссылка на метод
     * @param body Тело
     * @param mediaType Тип
     */
    fun post(url: String, body: String, mediaType: MediaType): String? {
        val client = OkHttpClient()
        val request: Request = Request.Builder()
            .url(url)
            .post(body.toRequestBody(mediaType))
            .build()
        var result: String? = null
        try {
            result = client.newCall(request).execute().body.string()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return result
    }

    /**
     * Отправляет POST запрос к серверу
     *
     * @param url Ссылка на метод
     * @param body Тело
     * @param mediaType Тип
     * @param defaultValue Переменная по умолчанию, если в ходе выполнения произошла ошибка
     */
    fun post(url: String, body: String, mediaType: MediaType, defaultValue: String): String {
        return post(url, body, mediaType) ?: defaultValue
    }

    /**
     * Отправляет POST запрос к серверу в формате JSON
     *
     * @param url Ссылка на метод
     * @param body Тело
     */
    fun postJson(url: String, body: String): String? {
        return post(url, body, JSON)
    }

    /**
     * Отправляет POST запрос к серверу в формате JSON
     *
     * @param url Ссылка на метод
     * @param body Тело
     * @param defaultValue Переменная по умолчанию, если в ходе выполнения произошла ошибка
     */
    fun postJson(url: String, body: String, defaultValue: String): String {
        return postJson(url, body) ?: defaultValue
    }

    fun get(url: String): String? {
        val client = OkHttpClient()
        val request: Request = Request.Builder()
            .url(url)
            .get()
            .build()
        var result: String? = null
        try {
            result = client.newCall(request).execute().body.string()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return result
    }

    fun get(url: String, defaultValue: String): String {
        return get(url) ?: defaultValue
    }

    fun <T> postJson(type: Type, url: String, body: String): T? {
        val res = postJson(url, body) ?: return null
        var result: T? = null
        try {
            result = Gson().fromJson(res, type)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return result
    }

    fun <T> postJson(type: Type, url: String, body: String, defaultValue: T): T {
        return postJson(type, url, body) ?: defaultValue
    }

    fun post(
        url: String,
        body: String,
        userName: String,
        userPassword: String,
        mediaType: MediaType
    ): String? {
        val encoding = Base64.encodeToString(
            "$userName:$userPassword".toByteArray(StandardCharsets.UTF_8),
            Base64.DEFAULT
        )
        val client = OkHttpClient()
        val request: Request = Request.Builder()
            .url(url)
            .addHeader("Authorization", "Basic $encoding")
            .post(body.toRequestBody(mediaType))
            .build()
        var result: String? = null
        try {
            result = client.newCall(request).execute().body.string()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return result
    }

    fun post(
        url: String,
        body: String,
        userName: String,
        userPassword: String,
        mediaType: MediaType,
        defaultValue: String
    ): String {
        return post(url, body, userName, userPassword, mediaType) ?: defaultValue
    }

    fun <T> post(
        type: Type,
        url: String,
        body: String,
        userName: String,
        userPassword: String,
        mediaType: MediaType
    ): T? {
        val res = post(url, body, userName, userPassword, mediaType) ?: return null
        var result: T? = null
        try {
            result = Gson().fromJson(res, type)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return result
    }

    fun <T> post(
        type: Type,
        url: String,
        body: String,
        userName: String,
        userPassword: String,
        mediaType: MediaType,
        defaultValue: T
    ): T {
        return post(type, url, body, userName, userPassword, mediaType) ?: defaultValue
    }
}