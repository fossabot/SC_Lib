package com.samplecode.example

import android.os.Bundle
import android.util.Log
import com.samplecode.example.databinding.ActivityMainBinding
import com.samplecode.lib.async.kotlin.AsyncActivity
import com.samplecode.lib.getString
import com.samplecode.lib.showToast
import com.samplecode.lib.states.FlowStateImpl
import com.samplecode.lib.states.toState
import com.samplecode.lib.styles.applyStyle
import com.samplecode.lib.utils.NotificationUtils
import kotlinx.coroutines.flow.flow

class MainActivity : AsyncActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val a = flow {
            emit(1)
            throw Exception("Some error")
        }

        a.toState(object : FlowStateImpl<Int> {
            override fun onSuccess(data: Int) {
                Log.i("Success", data.toString())
            }

            override fun onFailure(e: Exception) {
                Log.e("Error", e.getString())
            }
        })

        val notificationUtils = NotificationUtils.getInstance(this)

        binding.textBold.applyStyle {
            bold()
        }
        binding.textBoldLine.applyStyle {
            bold().lineThrough()
        }
        binding.textLine.applyStyle {
            lineThrough()
        }
        binding.textItalic.applyStyle {
            italic()
        }

        binding.buttonCreateChannel.setOnClickListener {
            notificationUtils.createChannel(
                channelId = "some",
                channelName = "Test",
                channelDescription = "For testing"
            )
            showToast("Канал уведомлений создан")
        }
        binding.buttonCreateGroupChannel.setOnClickListener {
            val groupId = "friends"
            notificationUtils.createGroup(groupId = groupId, "Друзья")
            notificationUtils.createChannel(
                channelId = "friend1",
                channelName = "Иван",
                channelDescription = "Уведомления от Ивана",
                groupId = groupId
            )
            notificationUtils.createChannel(
                channelId = "friend2",
                channelName = "Алексей",
                channelDescription = "Уведомления от Алексея",
                groupId = groupId
            )
            notificationUtils.createChannel(
                channelId = "friend3",
                channelName = "Олег",
                channelDescription = "Уведомления от Олега",
                groupId = groupId
            )
            showToast("Группа уведомлений создана")
        }
        binding.buttonShowNotification.setOnClickListener {
            notificationUtils.showNotification(
                channelId = "some",
                title = "Test",
                message = "Some text",
                icon = R.drawable.ic_android_black_24dp
            )
        }
    }
}