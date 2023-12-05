package com.lahsuak.apps.isroinfo.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

@Composable
fun CenterProgressBar() {
    Box(contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}