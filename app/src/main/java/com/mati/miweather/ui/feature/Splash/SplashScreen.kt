package com.mati.miweather.ui.feature.Splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.mati.miweather.R
import com.mati.miweather.ui.theme.Background
import com.mati.miweather.ui.theme.Background1
import com.mati.miweather.util.DataTime

@Composable
fun SplashScreen() {
    val backgroundColor = Brush.verticalGradient(
        0.0f to Background1, 1.0f to Background, startY = 0.0f, endY = 800.0f
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = backgroundColor)
    ) {
        val composition by rememberLottieComposition(
            LottieCompositionSpec.RawRes(
                loaderAnim()
            )
        )
        LottieAnimation(
            modifier = Modifier
                .size(270.dp, 270.dp)
                .align(Alignment.Center)
                .background(Color.Transparent),
            composition = composition,
            alignment = Alignment.Center,
            iterations = LottieConstants.IterateForever,
        )
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val anim by rememberLottieComposition(
                LottieCompositionSpec.RawRes(
                    R.raw.loader
                )
            )
            LottieAnimation(
                modifier = Modifier
                    .size(100.dp, 80.dp)
                    .padding(bottom = 32.dp)
                    .background(Color.Transparent),
                composition = anim,
                iterations = LottieConstants.IterateForever,
            )
        }
    }
}

@Composable
fun loaderAnim(): Int {
    var anim by remember { mutableStateOf(R.raw.day) }
    val time = DataTime.getHour()
    when (time) {
        in 5..18 -> {
            anim = R.raw.loader_day
        }

        else -> {
            anim = R.raw.loader_night
        }
    }
    return anim
}