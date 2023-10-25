package com.mati.miweather.ui.feature

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@SuppressLint("UnrememberedMutableState")
@Composable
fun MainScreen(
    viewModel: MainViewModel,
) {
    val response = viewModel.city.value

    if (response.isLoading){
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

    if (response.data?.name != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "شهر: ${response.data.name}"
            )
            Text(
                text = "دما: ${response.data.main.temp} کلوین"
            )
            val weather = response.data.weather[0].description
            Text(
                text = "وضعیت آب و هوا: ${weather}"
            )
        }
    } else {
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


}