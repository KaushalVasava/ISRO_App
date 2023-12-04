package com.lahsuak.apps.isroinfo.ui.components

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle

object TextUtil {
    fun getLinkText(text: String): AnnotatedString {
        return buildAnnotatedString {
            for (link in text
                .split(' ')) {
                if (link.matches(".*(#\\w+)|(http(s)?://.+).*".toRegex())) {
                    withStyle(SpanStyle(color = Color.Blue)) {
                        append("$link ")
                    }
                } else {
                    append("$link ")
                }
            }
        }
    }
}