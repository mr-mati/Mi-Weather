package com.mati.miweather.ui.feature.Forcast

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.mati.miweather.R
import com.mati.miweather.data.model.CityForecast
import com.mati.miweather.ui.theme.Transparent
import com.mati.miweather.ui.theme.White
import com.mati.miweather.util.BASE_IMAGE_URL
import com.mati.miweather.util.DataTime

@Composable
fun ListForecastItem(
    results: CityForecast.Main,
) {

    val temp = results.main.temp - 273.15
    val responseTemp = temp.toString().substring(0, 2)
    val data = results.dt_txt.substring(0, 10)
    val dayName = DataTime.getDayName(data).toString()

    Card(
        modifier = Modifier
            .width(180.dp)
            .height(130.dp)
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
            modifier = Modifier
                .fillMaxSize()
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
            Text(
                modifier = Modifier
                    .padding(bottom = 28.dp)
                    .align(Alignment.BottomCenter),
                text = dayName, style = TextStyle(
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                )
            )
            Text(
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .align(Alignment.BottomCenter),
                text = results.dt_txt, style = TextStyle()
            )
            Box {
                Row(
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier
                            .padding(start = 16.dp),
                        text = responseTemp, style = TextStyle(
                            fontSize = 64.sp,
                            color = White
                        )
                    )
                    Text(
                        text = "°C", style = TextStyle(
                            fontSize = 32.sp,
                            color = White
                        )
                    )
                    Image(
                        rememberAsyncImagePainter(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data("$BASE_IMAGE_URL${results.weather[0].icon}@2x.png")
                                .build()
                        ),
                        modifier = Modifier
                            .size(64.dp, 64.dp)
                            .padding(top = 46.dp),
                        contentScale = ContentScale.Crop,
                        contentDescription = ""
                    )
                }
                /*Text(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 8.dp, start = 8.dp,top = 68.dp),
                    text = results.weather[0].description, style = TextStyle(
                        fontSize = 26.sp,
                        color = White
                    )
                )*/
            }
        }
    }
}