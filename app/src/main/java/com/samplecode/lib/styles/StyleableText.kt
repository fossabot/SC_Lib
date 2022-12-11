package com.samplecode.lib.styles

import android.text.Spanned
import androidx.core.text.HtmlCompat

class StyleableText private constructor(
    private val source: String,
) {

    companion object {

        @JvmStatic
        fun builder(source: String): StyleableText {
            return StyleableText(source)
        }

        @JvmStatic
        fun builder(source: CharSequence): StyleableText {
            return builder(source.toString())
        }
    }

    private var styles = mutableListOf<String>()

    /**
     * Жирный текст
     */
    fun bold(): StyleableText {
        return addStyle("b")
    }

    /**
     * Зачеркнутый текст
     */
    fun lineThrough(): StyleableText {
        return addStyle("s")
    }

    /**
     * Наклонный текст
     */
    fun italic(): StyleableText {
        return addStyle("i")
    }

    /**
     * Подчеркнутый
     */
    fun underline(): StyleableText {
        return addStyle("u")
    }

    fun monospace(): StyleableText {
        return addStyle("tt")
    }

    fun addStyle(style: String): StyleableText {
        styles.add(style)
        return this
    }

    fun build(): Spanned {
        var startArgs = ""
        var endArgs = ""

        styles.forEach {
            startArgs += "<$it>"
            endArgs += "</$it>"
        }

        val s = "$startArgs$source$endArgs"
        return fromHtml(s)
    }

    private fun fromHtml(s: String): Spanned {
        return HtmlCompat.fromHtml(s, HtmlCompat.FROM_HTML_MODE_LEGACY)
    }
}