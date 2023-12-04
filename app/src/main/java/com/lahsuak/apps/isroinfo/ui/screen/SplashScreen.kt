package com.lahsuak.apps.isroinfo.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.lahsuak.apps.isroinfo.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onTimeout: () -> Unit) {
    LaunchedEffect(key1 = Unit) {
        delay(100L)
        onTimeout()
    }
    Image(
        painter = painterResource(id = R.drawable.isro_background),
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop,
        contentDescription = null
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewSplashScreen() {
    MaterialTheme {
        Surface(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            SplashScreen({})
        }
    }
}