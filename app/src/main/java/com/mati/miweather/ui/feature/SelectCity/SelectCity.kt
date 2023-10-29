package com.mati.miweather.ui.feature.SelectCity

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mati.miweather.ui.feature.MainViewModel
import com.mati.miweather.ui.theme.Background
import com.mati.miweather.ui.theme.Background1
import com.mati.miweather.ui.theme.Black
import com.mati.miweather.ui.theme.Primary
import com.mati.miweather.ui.theme.Transparent
import com.mati.miweather.ui.theme.White

@Composable
fun SelectCity(viewModel: MainViewModel, visible: () -> Unit) {

    val sharedPreferences = LocalContext.current.getSharedPreferences("City", Context.MODE_PRIVATE)

    val backgroundColor = Brush.verticalGradient(
        0.0f to Background1, 1.0f to Background, startY = 0.0f, endY = 800.0f
    )

    val cities =
        listOf(
            "تهران",
            "قم",
            "اصفهان",
            "همدان",
            "مشهد",
            "کرج",
            "یزد",
            "شیراز",
            "ساری",
            "اردبیل",
            "سبرجان",
            "شهرکرد",
            "رشت",
            "گرگان",
            "اراک",
            "یاسوج",
            "تبریز",
            "کرمان",
            "کاشان",
            "کرمانشاه",
            "گلستان",
            "زاهدان",
            "هرمزگان",
            "اهواز",
            "سنندج",
            "بندر عباس"
        )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Transparent
            )
            .padding(32.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .background(brush = backgroundColor),
        ) {
            Text(
                modifier = Modifier
                    .padding(start = 16.dp, top = 8.dp, bottom = 4.dp)
                    .align(Alignment.Start),
                fontWeight = FontWeight.Bold,
                text = "City Selected", style = TextStyle(
                    color = Primary, fontSize = 24.sp
                )
            )
            LazyColumn(
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 16.dp)
            ) {
                items(cities) { city ->
                    CityNameItem(city) {
                        visible()
                        viewModel.newData(city)
                        sharedPreferences.edit().putString("City", city).apply()
                    }
                }
            }
        }
    }

}

@Composable
fun CityNameItem(cityName: String, onclick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .background(Transparent)
            .clickable {
                onclick()
            }
            .padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = White,
        ),
        shape = RoundedCornerShape(16.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(16.dp),
                text = cityName,
                style = TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 26.sp,
                    color = Black
                )
            )
        }
    }
}
