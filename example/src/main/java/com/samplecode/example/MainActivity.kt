package com.samplecode.example

import android.os.Bundle
import android.util.Log
import com.samplecode.example.databinding.ActivityMainBinding
import com.samplecode.lib.*
import com.samplecode.lib.async.kotlin.AsyncActivity
import com.samplecode.lib.states.FlowStateImpl
import com.samplecode.lib.states.toState
import com.samplecode.lib.styles.applyStyle
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
            createChannel("some", "Test", "For testing")
            showToast("Канал уведомлений создан")
        }
        binding.buttonCreateGroupChannel.setOnClickListener {
            val groupId = "friends"
            createGroup(groupId, "Друзья")
            createChannel("friend1", "Иван", "Уведомления от Ивана", groupId = groupId)
            createChannel("friend2", "Алексей", "Уведомления от Алексея", groupId = groupId)
            createChannel("friend3", "Олег", "Уведомления от Олега", groupId = groupId)
            showToast("Группа уведомлений создана")
        }
        binding.buttonShowNotification.setOnClickListener {
            showNotification("some", "Test", "Some text", R.drawable.ic_android_black_24dp)
        }
    }
}