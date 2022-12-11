package com.samplecode.lib.styles

import android.widget.TextView

fun TextView.styleableBuilder(): StyleableText {
    return StyleableText.builder(this.text)
}

fun CharSequence.styleableBuilder(): StyleableText {
    return StyleableText.builder(this)
}