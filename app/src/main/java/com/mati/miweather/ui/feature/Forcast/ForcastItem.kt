@file:OptIn(ExperimentalMaterial3Api::class)

package com.mati.miweather.ui.feature.Forcast

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.mati.miweather.R
import com.mati.miweather.data.model.CityForecast
import com.mati.miweather.util.BASE_IMAGE_URL
import com.mati.miweather.util.DataTime
import com.mati.miweather.util.USER_LANGUAGE

@Composable
fun ListForecastItem(
    response: List<CityForecast.Main>?,
) {

    val scrollState = rememberScrollState()

    val results = response!![0]

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(start = 16.dp, end = 16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onTertiary,
        ),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
    ) {
        val data = remember { mutableStateOf(DataTime.getCurrentData()) }
        Column {
            Row(
                modifier = Modifier
                    .horizontalScroll(scrollState)
                    .padding(bottom = 4.dp, top = 8.dp, end = 8.dp)
            ) {
                response.forEach {
                    if (it.dt_txt.substring(11, 19) == "21:00:00") {
                        DayShowing(it, data.value) {
                            data.value = it.dt_txt.substring(0, 10)
                        }
                    }
                }
            }
            LazyRow(
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp, start = 4.dp)
            ) {
                items(
                    items = response,
                    key = {
                        it.dt
                    },
                ) { response ->
                    if (response.dt_txt.substring(0, 10) == data.value) {
                        ItemForecast(results = response)
                    }
                }
            }
        }
    }
}


@Composable
fun DayShowing(results: CityForecast.Main, selection: String, clickable: () -> Unit) {
    val data = results.dt_txt.substring(0, 10)

    val dayName = remember { mutableStateOf("Today") }
    if (USER_LANGUAGE == "fa") {
        dayName.value = DataTime.convertDayHijri(data)
    } else {
        dayName.value = DataTime.getDayName(data).toString()
    }

    if (data == selection) {
        Card(
            modifier = Modifier
                .padding(start = 8.dp, end = 4.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 0.dp
            ),
            onClick = { clickable() },
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.onSurface,
            ),
            shape = RoundedCornerShape(14.dp)
        ) {
            Text(
                modifier = Modifier.padding(top = 8.dp, bottom = 4.dp, start = 16.dp, end = 16.dp),
                text = dayName.value,
                fontWeight = FontWeight.Bold,
                style = TextStyle(
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    letterSpacing = 0.4.sp
                )
            )
        }
    } else {
        Card(
            modifier = Modifier
                .padding(start = 8.dp, end = 4.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 0.dp
            ),
            onClick = { clickable() },
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primary,
            ),
            shape = RoundedCornerShape(14.dp)
        ) {
            Text(
                modifier = Modifier.padding(top = 8.dp, bottom = 4.dp, start = 16.dp, end = 16.dp),
                text = dayName.value,
                fontWeight = FontWeight.Bold,
                style = TextStyle(
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    letterSpacing = 0.4.sp
                )
            )
        }
    }
}

@Composable
fun ItemForecast(results: CityForecast.Main) {

    val data = results.dt_txt.substring(0, 10)
    val time = results.dt_txt.substring(11, 16)

    val dayName = remember { mutableStateOf("Today") }
    if (USER_LANGUAGE == "fa") {
        dayName.value = DataTime.convertDayHijri(data)
    } else {
        dayName.value = DataTime.getDayName(data).toString()
    }

    val dataName = remember { mutableStateOf("Today") }
    if (USER_LANGUAGE == "fa") {
        dataName.value = DataTime.convertGregorianToHijri(data)
    } else {
        dataName.value = results.dt_txt.substring(0, 10)
    }

    val temp = results.main.temp - 273.15

    val responseTemp: String = if (temp >= 10 && temp < 100) {
        temp.toString().substring(0, 2)
    } else if (temp >= 0 && temp < 10) {
        temp.toString().substring(0, 1)
    } else if (temp > -10 && temp < 0) {
        temp.toString().substring(0, 2)
    } else {
        temp.toString().substring(0, 3)
    }

    Card(
        modifier = Modifier
            .width(180.dp)
            .fillMaxHeight()
            .padding(end = 4.dp)
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Transparent,
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .blur(
                        radiusX = 20.dp,
                        radiusY = 20.dp,
                        edgeTreatment = BlurredEdgeTreatment(RoundedCornerShape(16.dp))
                    ),
                contentScale = ContentScale.FillBounds,
                painter = painterResource(id = R.drawable.bg_item_forcast),
                contentDescription = ""
            )
            Image(
                rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("$BASE_IMAGE_URL${results.weather[0].icon}@2x.png")
                        .build()
                ),
                modifier = Modifier
                    .size(100.dp, 100.dp),
                contentScale = ContentScale.Crop,
                contentDescription = ""
            )
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier.padding(start = 16.dp),
                        text = responseTemp,
                        style = TextStyle(
                            fontSize = 64.sp, color = White
                        )
                    )
                    Text(
                        text = "°C", style = TextStyle(
                            fontSize = 32.sp, color = White
                        )
                    )
                }
                Text(
                    modifier = Modifier
                        .padding(start = 6.dp, end = 8.dp),
                    text = results.weather[0].description,
                    style = TextStyle(
                        textAlign = TextAlign.Center,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = White,
                    )
                )
                Text(
                    text = dayName.value,
                    style = TextStyle(
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = White,
                    )
                )
                Text(
                    modifier = Modifier
                        .padding(bottom = 8.dp),
                    text = "${dataName.value} $time",
                    style = TextStyle(
                        MaterialTheme.colorScheme.onSurface,
                    )
                )
            }
        }
    }
}