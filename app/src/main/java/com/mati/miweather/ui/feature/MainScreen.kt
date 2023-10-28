package com.mati.miweather.ui.feature

import android.annotation.SuppressLint
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.mati.miweather.R
import com.mati.miweather.ui.feature.Forcast.ListForecastItem
import com.mati.miweather.ui.feature.Header.Header
import com.mati.miweather.ui.feature.StatusBar.StatusBar
import com.mati.miweather.ui.theme.Background
import com.mati.miweather.ui.theme.Background1
import com.mati.miweather.ui.theme.Day
import com.mati.miweather.ui.theme.Day1
import com.mati.miweather.ui.theme.Magic
import com.mati.miweather.ui.theme.Magic1
import com.mati.miweather.ui.theme.Morning
import com.mati.miweather.ui.theme.Morning1
import com.mati.miweather.ui.theme.Night
import com.mati.miweather.ui.theme.Night1
import com.mati.miweather.ui.theme.Primary
import com.mati.miweather.ui.theme.Transparent
import com.mati.miweather.util.DataTime

@SuppressLint("UnrememberedMutableState")
@Composable
fun MainScreen(
    viewModel: MainViewModel,
) {
    val response = viewModel.city.value
    val data = viewModel.city.value.data

    val responseForecast = viewModel.cityForecast.value
    val listForecast = responseForecast.data?.list

    val systemUiController = rememberSystemUiController()
    systemUiController.isNavigationBarVisible = false
    systemUiController.setNavigationBarColor(Background1)
    systemUiController.setStatusBarColor(Background1)

    if (response.isLoading && responseForecast.isLoading) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                color = Color.Blue, // You can set the color of the CircularProgressIndicator
                strokeWidth = 5.dp, // You can adjust the stroke width
            )
        }
    }

    val backgroundColor = Brush.verticalGradient(
        0.0f to Background1, 1.0f to Background, startY = 0.0f, endY = 800.0f
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = backgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (data?.name != null) {
            Header(response = data)
            StatusBar(response = data)
        }

        if (responseForecast.data?.list != null) {
            Text(
                modifier = Modifier
                    .padding(start = 16.dp, top = 8.dp, bottom = 4.dp)
                    .align(Alignment.Start),
                fontWeight = FontWeight.Bold,
                text = "Forecast 5 Day", style = TextStyle(
                    color = Primary, fontSize = 24.sp
                )
            )
            LazyRow(
            ) {
                items(
                    items = listForecast!!,
                    key = {
                        it.dt
                    },
                ) { response ->
                    ListForecastItem(results = response)
                }
            }
        }

    }

}
