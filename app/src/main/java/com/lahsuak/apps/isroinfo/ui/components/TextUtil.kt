package com.lahsuak.apps.isroinfo.ui.components

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle

object TextUtil {
    fun getBoldText(text: String): AnnotatedString {
        return buildAnnotatedString {
            append(text.substringBefore(":"))
            append(":")
            if (text.contains(':')) {
                withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("${text.substringAfter(":")} ")
                }
            }
        }
    }
}