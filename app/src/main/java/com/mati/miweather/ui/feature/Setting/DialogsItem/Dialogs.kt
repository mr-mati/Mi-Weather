package com.mati.miweather.ui.feature.Setting.DialogsItem

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
import com.mati.miweather.ui.theme.Background
import com.mati.miweather.ui.theme.Background1
import com.mati.miweather.ui.theme.Black
import com.mati.miweather.ui.theme.Blue
import com.mati.miweather.ui.theme.Primary
import com.mati.miweather.ui.theme.Red
import com.mati.miweather.ui.theme.Transparent

@Composable
fun Success(
    title: String,
    subTitle: String,
    visible: () -> Unit,
) {
    val context = LocalContext.current

    val backgroundColor = Brush.verticalGradient(
        0.0f to Background1, 1.0f to Background, startY = 0.0f, endY = 800.0f
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
            modifier = Modifier.background(brush = backgroundColor),
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
                fontWeight = FontWeight.Bold, text = title, style = TextStyle(
                    color = Primary, fontSize = 24.sp
                )
            )
            Text(
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                text = subTitle,
                style = TextStyle(
                    color = Primary, fontSize = 18.sp
                )
            )
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp), color = Black
            ) {}
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                TextButton(modifier = Modifier.padding(end = 34.dp), onClick = { visible() }) {
                    Text(
                        text = stringResource(R.string.later), style = TextStyle(
                            color = Red, fontSize = 18.sp, textAlign = TextAlign.Center
                        )
                    )
                }
                Surface(
                    modifier = Modifier
                        .width(1.dp)
                        .height(50.dp), color = Black
                ) {}
                TextButton(
                    modifier = Modifier.padding(start = 34.dp),
                    onClick = {
                        (context as? Activity)?.recreate()
                    }) {
                    Text(
                        text = stringResource(R.string.restart), style = TextStyle(
                            color = Blue, fontSize = 18.sp, textAlign = TextAlign.Center
                        )
                    )
                }
            }
        }
    }
}