package com.samplecode.lib.utils

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Build
import android.view.Window
import android.view.WindowInsets
import android.view.WindowManager
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

object CommonUtils {

    @JvmStatic
    fun <T> collectionIsNullOrEmpty(collection: Collection<T>?): Boolean {
        return collection.isNullOrEmpty()
    }

    @JvmStatic
    fun toIntOrNull(value: String?): Int? {
        return value?.toIntOrNull()
    }

    @JvmStatic
    fun toIntOrNull(value: Any?): Int? {
        return value as? Int
    }

    @JvmStatic
    fun toInt(value: Any?, defaultValue: Int): Int {
        return toIntOrNull(value) ?: defaultValue
    }

    @JvmStatic
    fun toInt(value: String?, defaultValue: Int): Int {
        return toIntOrNull(value) ?: defaultValue
    }

    @JvmStatic
    fun toDoubleOrNull(value: String?): Double? {
        return value?.toDoubleOrNull()
    }

    @JvmStatic
    fun toDoubleOrNull(value: Any?): Double? {
        return value as? Double
    }

    @JvmStatic
    fun toDouble(value: Any?, defaultValue: Double): Double {
        return toDoubleOrNull(value) ?: defaultValue
    }

    @JvmStatic
    fun toDouble(value: String?, defaultValue: Double): Double {
        return toDoubleOrNull(value) ?: defaultValue
    }

    @JvmStatic
    @JvmOverloads
    fun copyToClipboard(context: Context, text: String, label: String = "") {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText(label, text)
        clipboard.setPrimaryClip(clip)
    }

    @JvmStatic
    fun hideStatusBar(activity: Activity?) {
        if (activity == null) {
            return
        }
        try {
            activity.requestWindowFeature(Window.FEATURE_NO_TITLE)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                activity.window.insetsController?.hide(WindowInsets.Type.statusBars())
            } else {
                @Suppress("DEPRECATION")
                activity.window.setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            @Suppress("DEPRECATION")
            activity.window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        /*activity.window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        activity.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )*/
    }

    @JvmStatic
    fun hideNavigation(activity: Activity?) {
        activity?.window?.let { window ->
            WindowCompat.setDecorFitsSystemWindows(window, false)
            WindowInsetsControllerCompat(window, activity.findViewById(android.R.id.content)).let { controller ->
                controller.hide(WindowInsetsCompat.Type.systemBars())
                controller.systemBarsBehavior =
                    WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        }
        /*if (activity?.window != null) {
            try {
                WindowCompat.setDecorFitsSystemWindows(activity.window, false)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            activity.window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        }*/
    }

    @JvmStatic
    fun setFullscreen(activity: Activity?) {
        hideNavigation(activity)
        hideStatusBar(activity)
    }
}