package com.mati.miweather.ui.feature.Setting.DialogsItem

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.mati.miweather.R
import com.mati.miweather.ui.theme.Transparent
import com.mati.miweather.ui.theme.green

@Composable
fun Success(
    visible: () -> Unit,
) {
    val backgroundColor = Brush.verticalGradient(
        0.0f to MaterialTheme.colorScheme.primary,
        1.0f to MaterialTheme.colorScheme.secondary,
        startY = 0.0f,
        endY = 800.0f
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Transparent
            )
            .padding(32.dp), elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ), shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .clickable {
                    visible()
                }
                .background(brush = backgroundColor),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val composition by rememberLottieComposition(
                LottieCompositionSpec.RawRes(
                    R.raw.success
                )
            )
            LottieAnimation(
                modifier = Modifier
                    .size(120.dp, 120.dp)
                    .background(Color.Transparent),
                composition = composition,
                alignment = Alignment.Center,
                restartOnPlay = false,
                contentScale = ContentScale.Crop
            )
            Text(
                fontWeight = FontWeight.Bold,
                text = stringResource(R.string.success),
                style = TextStyle(
                    color = green, fontSize = 24.sp
                )
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = stringResource(R.string.change_setting_success),
                style = TextStyle(
                    MaterialTheme.colorScheme.onPrimary, fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            )
            Text(
                modifier = Modifier.padding(8.dp),
                text = stringResource(R.string.change_restart_success),
                style = TextStyle(
                    MaterialTheme.colorScheme.onSurface, fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )
            )
        }
    }
}