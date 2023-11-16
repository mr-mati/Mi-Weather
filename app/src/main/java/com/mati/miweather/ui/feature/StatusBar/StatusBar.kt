package com.mati.miweather.ui.feature.StatusBar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mati.miweather.R
import com.mati.miweather.data.model.CitysStatus

@Composable
fun StatusBar(response: CitysStatus) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(start = 24.dp, end = 24.dp, top = 16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onTertiary,
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .background(Color.Transparent)
                    .align(Alignment.Center)
            ) {
                ItemOther(
                    "${response.wind.speed} " + stringResource(R.string.kilometers),
                    stringResource(R.string.windSpeed),
                    R.drawable.wind
                )
                ItemOther(
                    "${response.main.humidity} " + stringResource(R.string.percent),
                    stringResource(R.string.humidity),
                    R.drawable.humidity
                )
                ItemOther(
                    "${response.main.pressure} " + stringResource(R.string.mb),
                    stringResource(R.string.pressure),
                    R.drawable.pressure
                )
            }
        }
    }
}

@Composable
fun ItemOther(data: String, text: String, icon: Int) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(end = 16.dp, start = 16.dp),
    ) {
        Image(painter = painterResource(id = icon), contentDescription = "Icon")
        Text(
            modifier = Modifier
                .padding(top = 4.dp, end = 4.dp),
            text = data,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.surface,
                textAlign = TextAlign.Center,
                fontSize = 18.sp
            )
        )
        Text(
            text = text,
            style = TextStyle(
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
                fontSize = 14.sp
            )
        )
    }
}