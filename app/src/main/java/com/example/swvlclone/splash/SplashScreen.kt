package com.example.swvlclone.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.swvlclone.R
import com.example.swvlclone.ui.theme.SwvlCloneTheme

@Composable
fun SplashScreen() {
    Surface(
        color = MaterialTheme.colorScheme.primary
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                alignment = Alignment.Center,

                painter = painterResource(id = R.drawable.swvl_seeklogo),
                contentDescription = "Swvl Logo",
                modifier = Modifier.align(Alignment.Center)
            )
        }

    }
}

@Preview
@Composable
private fun SplashScreenPreview() {
    SwvlCloneTheme {
        SplashScreen()
    }
}