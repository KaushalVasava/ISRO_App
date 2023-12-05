package com.lahsuak.apps.isroinfo.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

@Composable
fun FullScreenCenterButton(text: String, onClick: () -> Unit) {
    Box(contentAlignment = Alignment.Center) {
        Button(onClick = {
            onClick()
        }) {
            Text(text)
        }
    }
}