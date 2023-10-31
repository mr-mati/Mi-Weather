package com.mati.miweather.ui.feature.Error

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mati.miweather.ui.theme.Background
import com.mati.miweather.ui.theme.Background1

@Composable
fun ErrorPage(TextError: String, TextError2: String, onclickRetry: () -> Unit) {
    val backgroundColor = Brush.verticalGradient(
        0.0f to Background1, 1.0f to Background, startY = 0.0f, endY = 800.0f
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = backgroundColor)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Error",
            fontSize = 24.sp,
            color = Color.Red
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = TextError,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = TextError2,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                onclickRetry()
            },
            contentPadding = PaddingValues(16.dp)
        ) {
            Text(text = "Retry")
        }
    }
}