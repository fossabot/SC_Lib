package com.samplecode.lib

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationChannelGroupCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlin.random.Random

class ApplicationUtil private constructor() {

    private var notificationManager: NotificationManagerCompat? = null

    private var notificationBuilder: NotificationCompat.Builder? = null

    companion object {

        var INSTANCE: ApplicationUtil? = null

        fun init(context: Context) {
            INSTANCE = ApplicationUtil().apply {
                notificationManager = NotificationManagerCompat.from(context)
                notificationBuilder = NotificationCompat.Builder(context, "")
            }
        }
    }

    fun createChannel(
        channelId: String,
        channelName: String,
        channelDescription: String = "",
        importance: Int = NotificationManagerCompat.IMPORTANCE_HIGH,
        groupId: String? = null,
    ) {
        if (notificationManager == null) {
            return
        }
        val channel = NotificationChannelCompat.Builder(channelId, importance).apply {
            setName(channelName)
            setDescription(channelDescription)
            setGroup(groupId)
        }.build()
        notificationManager!!.createNotificationChannel(channel)
    }

    fun createGroup(groupId: String, groupName: String, groupDescription: String = "") {
        if (notificationManager == null) {
            return
        }
        val group = NotificationChannelGroupCompat.Builder(groupId).apply {
            setName(groupName)
            setDescription(groupDescription)
        }.build()
        notificationManager!!.createNotificationChannelGroup(group)
    }

    fun showNotification(
        channelId: String,
        title: String,
        body: String,
        @DrawableRes icon: Int,
        id: Int = Random.nextInt(0, 5000),
    ) {
        if (notificationBuilder == null || notificationManager == null) {
            return
        }
        val notification = notificationBuilder!!.apply {
            setChannelId(channelId)
            setContentTitle(title)
            setContentText(body)
            setSmallIcon(icon)
        }.build()
        notificationManager!!.notify(id, notification)
    }
}

fun createGroup(groupId: String, groupName: String, groupDescription: String = "") {
    ApplicationUtil.INSTANCE?.createGroup(groupId, groupName, groupDescription)
}

fun createChannel(
    channelId: String,
    channelName: String,
    channelDescription: String = "",
    importance: Int = NotificationManagerCompat.IMPORTANCE_HIGH,
    groupId: String? = null,
) {
    ApplicationUtil.INSTANCE?.createChannel(channelId,
        channelName,
        channelDescription,
        importance,
        groupId)
}

fun showNotification(
    channelId: String,
    title: String,
    body: String,
    @DrawableRes icon: Int,
    id: Int = Random.nextInt(0, 5000)
) {
    ApplicationUtil.INSTANCE?.showNotification(channelId, title, body, icon, id)
}