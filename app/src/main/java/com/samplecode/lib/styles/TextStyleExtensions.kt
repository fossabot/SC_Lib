package com.samplecode.lib.styles

import android.widget.TextView

fun TextView.styleableBuilder(): StyleableText {
    return StyleableText.builder(this.text)
}

fun CharSequence.styleableBuilder(): StyleableText {
    return StyleableText.builder(this)
}

fun TextView.applyStyle(block: StyleableText.() -> StyleableText) {
    val builder = StyleableText.builder(this.text)
    this.text = block(builder).build()
}