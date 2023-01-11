package com.samplecode.lib.utils

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationChannelGroupCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlin.random.Random

class NotificationUtils private constructor(context: Context) {

    companion object {

        @Volatile
        private var INSTANCE: NotificationUtils? = null

        fun getInstance(context: Context): NotificationUtils {
            return INSTANCE ?: synchronized(this) {
                INSTANCE = NotificationUtils(context)
                INSTANCE!!
            }
        }
    }

    private var notificationManager: NotificationManagerCompat =
        NotificationManagerCompat.from(context)

    private var notificationBuilder: NotificationCompat.Builder =
        NotificationCompat.Builder(context, "")

    fun getChannels(): List<NotificationChannelCompat> {
        return notificationManager.notificationChannelsCompat
    }

    fun changeChannelImportance(channelId: String, newImportance: Int) {
        val channel = notificationManager
            .getNotificationChannelCompat(channelId) ?: return
        createChannel(
            channelId = channelId,
            importance = newImportance,
            channelName = channel.name?.toString(),
            channelDescription = channel.description,
            groupId = channel.group
        )
    }

    fun createGroup(groupId: String, groupName: String, groupDescription: String = "") {
        val group = NotificationChannelGroupCompat
            .Builder(groupId)
            .setName(groupName)
            .setDescription(groupDescription)
            .build()
        notificationManager.createNotificationChannelGroup(group)
    }

    fun createChannel(
        channelId: String,
        channelName: String? = null,
        channelDescription: String? = null,
        groupId: String? = null,
        importance: Int = NotificationManagerCompat.IMPORTANCE_DEFAULT
    ) {
        val channel = NotificationChannelCompat
            .Builder(channelId, importance)
            .setName(channelName)
            .setDescription(channelDescription)
            .setGroup(groupId)
            .build()
        notificationManager.createNotificationChannel(channel)
    }

    fun showNotification(
        channelId: String,
        title: String,
        message: String,
        @DrawableRes icon: Int,
        id: Int = Random.nextInt(0, 5000)
    ) {
        val notification = notificationBuilder.apply {
            setChannelId(channelId)
            setContentTitle(title)
            setContentText(message)
            setSmallIcon(icon)
        }.build()
        notificationManager.notify(id, notification)
    }
}