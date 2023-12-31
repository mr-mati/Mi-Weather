package com.mati.miweather.ui.feature.Header

import android.annotation.SuppressLint
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.mati.miweather.R
import com.mati.miweather.data.model.CitysStatus
import com.mati.miweather.ui.theme.Day
import com.mati.miweather.ui.theme.Day1
import com.mati.miweather.ui.theme.Magic
import com.mati.miweather.ui.theme.Magic1
import com.mati.miweather.ui.theme.Morning
import com.mati.miweather.ui.theme.Morning1
import com.mati.miweather.ui.theme.Night
import com.mati.miweather.ui.theme.Night1
import com.mati.miweather.ui.theme.Transparent
import com.mati.miweather.util.DataTime
import kotlinx.coroutines.delay

@Composable
fun Header(response: CitysStatus, onclick: () -> Unit, onclickSetting: () -> Unit) {
    val data = DataTime.getCurrentData()
    val monthName = DataTime.getGregorianMonthName()
    val dayName = DataTime.getDayName(data).toString()
    val day = DataTime.getDay()

    var time by remember { mutableStateOf(DataTime.getCurrentTime()) }
    LaunchedEffect(Unit) {
        while (true) {
            delay(10000L)
            time = DataTime.getCurrentTime()
        }
    }

    val temp = response.main.temp - 273.15

    val responseTemp: String = if (temp >= 10 && temp < 100) {
        temp.toString().substring(0, 2)
    } else if (temp >= 0 && temp < 10) {
        temp.toString().substring(0, 1)
    } else if (temp > -10 && temp < 0) {
        temp.toString().substring(0, 2)
    } else {
        temp.toString().substring(0, 3)
    }

    Box(
        contentAlignment = Alignment.TopEnd,
        modifier = Modifier
            .padding(end = 16.dp, top = 16.dp)
    ) {
        Icon(
            modifier = Modifier
                .size(32.dp)
                .clickable {
                    onclickSetting()
                }
                .align(Alignment.TopEnd),
            painter = painterResource(id = R.drawable.setting),
            tint = MaterialTheme.colorScheme.onPrimary,
            contentDescription = ""
        )
        Column(
            modifier = Modifier.padding(top = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextButton(onClick = { onclick() }) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = response.name, style = TextStyle(
                            color = MaterialTheme.colorScheme.surface, fontSize = 24.sp
                        )
                    )
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        tint = MaterialTheme.colorScheme.onPrimary,
                        contentDescription = ""
                    )
                }
            }
            Text(
                modifier = Modifier.padding(top = 2.dp, bottom = 4.dp),
                text = "$dayName, $day $monthName $time",
                style = TextStyle(
                    color = Color.Gray, fontSize = 14.sp
                )
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent),
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .background(
                            Transparent
                        )
                        .padding(start = 48.dp, end = 48.dp, top = 4.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 6.dp
                    ),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .background(
                                brush = featureBackground()
                            )
                    ) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .align(Alignment.TopCenter)
                                .background(
                                    Transparent
                                )
                                .padding(4.dp),
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 6.dp
                            ),
                            shape = RoundedCornerShape(14.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .zIndex(0f)
                                    .background(
                                        brush = featureColor()
                                    ),
                            ) {
                                Row(
                                    modifier = Modifier
                                        .background(Color.Transparent)
                                        .align(Alignment.TopEnd),
                                    horizontalArrangement = Arrangement.End
                                ) {
                                    Text(
                                        modifier = Modifier
                                            .padding(top = 8.dp, end = 16.dp),
                                        text = stringResource(R.string.centigrade),
                                        style = TextStyle(
                                            fontWeight = FontWeight.Bold,
                                            color = Color.White,
                                            textAlign = TextAlign.Center,
                                            fontSize = 48.sp
                                        )
                                    )
                                }
                                FeatureAnimation()
                                val weather = response.weather[0].description
                                Text(
                                    modifier = Modifier
                                        .size(150.dp)
                                        .align(Alignment.BottomEnd)
                                        .padding(top = 68.dp, end = 8.dp, start = 8.dp),
                                    text = weather,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis,
                                    style = TextStyle(
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White,
                                        textAlign = TextAlign.Center,
                                        fontSize = 26.sp
                                    )
                                )
                            }
                        }
                    }
                }
                Text(
                    modifier = Modifier
                        .align(Alignment.TopCenter),
                    text = responseTemp,
                    maxLines = 1,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 150.sp
                    )
                )
                val composition by rememberLottieComposition(
                    LottieCompositionSpec.RawRes(
                        imageFeature(
                            response
                        )
                    )
                )
                LottieAnimation(
                    modifier = Modifier
                        .size(270.dp, 270.dp)
                        .padding(top = 70.dp, end = 100.dp)
                        .align(Alignment.BottomStart)
                        .background(Color.Transparent),
                    composition = composition,
                    alignment = Alignment.Center,
                    iterations = LottieConstants.IterateForever,
                )
            }
        }
    }
}


@Composable
fun imageFeature(response: CitysStatus): Int {

    var anim by remember { mutableStateOf(R.raw.day) }
    val time = DataTime.getHour()
    val icon = response.weather[0].icon
    when (time) {
        in 5..17 -> {
            when (icon) {
                "01d" -> anim = R.raw.day
                "02d" -> anim = R.raw.day_clouds
                "03d" -> anim = R.raw.clouds
                "04d" -> anim = R.raw.clouds
                "09d" -> anim = R.raw.day_rain
                "10d" -> anim = R.raw.day_rain
                "11d" -> anim = R.raw.thunderstorm
                "13d" -> anim = R.raw.day_snow
                "50d" -> anim = R.raw.mist
            }
        }

        else -> {
            when (icon) {
                "01n" -> anim = R.raw.night
                "02n" -> anim = R.raw.night_clouds
                "03n" -> anim = R.raw.clouds
                "04n" -> anim = R.raw.clouds
                "09n" -> anim = R.raw.night_rain
                "10n" -> anim = R.raw.night_rain
                "11n" -> anim = R.raw.thunderstorm
                "13n" -> anim = R.raw.night_snow
                "50n" -> anim = R.raw.mist
            }
        }
    }
    return anim
}

@Composable
fun featureBackground(): Brush {
    val time = DataTime.getHour()
    lateinit var brush: Brush
    when (time) {
        in 6..12 -> {
            brush = Brush.verticalGradient(
                0.0f to Morning, 1.0f to Morning1, startY = 0.0f, endY = 800.0f
            )
        }

        in 12..19 -> {
            brush = Brush.verticalGradient(
                0.0f to Day, 1.0f to Day1, startY = 0.0f, endY = 800.0f
            )
        }

        in 19..24 -> {
            brush = Brush.verticalGradient(
                0.0f to Night, 1.0f to Night1, startY = 0.0f, endY = 800.0f
            )
        }

        in 0..6 -> {
            brush = Brush.verticalGradient(
                0.0f to Magic, 1.0f to Magic1, startY = 0.0f, endY = 800.0f
            )
        }
    }
    return brush
}

@Composable
fun featureColor(): Brush {
    val time = DataTime.getHour()
    lateinit var brush: Brush
    when (time) {
        in 6..12 -> {
            brush = Brush.verticalGradient(
                0.0f to Morning1, 1.0f to Morning, startY = 0.0f, endY = 800.0f
            )
        }

        in 12..18 -> {
            brush = Brush.verticalGradient(
                0.0f to Day1, 1.0f to Day, startY = 0.0f, endY = 800.0f
            )
        }

        in 18..24 -> {
            brush = Brush.verticalGradient(
                0.0f to Night1, 1.0f to Night, startY = 0.0f, endY = 800.0f
            )
        }

        in 0..6 -> {
            brush = Brush.verticalGradient(
                0.0f to Magic1, 1.0f to Magic, startY = 0.0f, endY = 800.0f
            )
        }
    }
    return brush
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun FeatureAnimation() {
    var imageAlpha by remember { mutableStateOf(1f) }
    val infiniteTransition = rememberInfiniteTransition()

    val alphaAnimation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    imageAlpha = alphaAnimation

    when (DataTime.getHour()) {
        in 6..18 -> {
            val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.day_effect))
            LottieAnimation(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent),
                composition = composition,
                alignment = Alignment.Center,
                iterations = LottieConstants.IterateForever,
            )
        }

        in 18..24 -> {
            Image(
                painter = painterResource(id = R.drawable.star_feature),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Transparent)
                    .alpha(imageAlpha)
            )
            val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.night_effect))
            LottieAnimation(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent),
                composition = composition,
                alignment = Alignment.Center,
                iterations = LottieConstants.IterateForever,
            )
        }

        in 0..6 -> {
            Image(
                painter = painterResource(id = R.drawable.star_feature),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Transparent)
                    .alpha(imageAlpha)
            )
            val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.night_effect))
            LottieAnimation(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent),
                composition = composition,
                alignment = Alignment.Center,
                iterations = LottieConstants.IterateForever,
            )
        }
    }
}