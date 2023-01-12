@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package com.samplecode.lib.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast
import androidx.core.content.ContextCompat

class PermissionUtils(
    private val context: Context
) {

    companion object {

        @JvmStatic
        fun create(context: Context): PermissionUtils {
            return PermissionUtils(context)
        }
    }

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.TIRAMISU)
    fun requirePermissionNotification(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
    }

    fun checkNotificationPermission(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        } else {
            true
        }
    }

    fun checkPermission(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    fun checkPermissions(vararg permissions: String): Boolean {
        for (permission in permissions) {
            if (!checkPermission(permission)) {
                return false
            }
        }

        return true
    }
}