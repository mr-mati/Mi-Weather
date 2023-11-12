package com.mati.miweather.ui.feature.Setting.DialogsItem

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mati.miweather.R
import com.mati.miweather.ui.feature.MainViewModel
import com.mati.miweather.ui.theme.Background
import com.mati.miweather.ui.theme.Background1
import com.mati.miweather.ui.theme.Black
import com.mati.miweather.ui.theme.Primary
import com.mati.miweather.ui.theme.Transparent
import com.mati.miweather.ui.theme.White
import com.mati.miweather.ui.theme.onPrimary
import com.mati.miweather.util.USER_LANGUAGE

@Composable
fun DialogLanguage(
    visible: () -> Unit,
) {

    val context = LocalContext.current

    val backgroundColor = Brush.verticalGradient(
        0.0f to Background1, 1.0f to Background, startY = 0.0f, endY = 800.0f
    )

    val listLanguage =
        listOf(
            "فارسی",
            "English"
        )

    var tickEn by remember { mutableStateOf(false) }
    var tickFa by remember { mutableStateOf(false) }


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
            Row(
                modifier = Modifier
                    .padding(start = 16.dp, top = 8.dp, bottom = 4.dp)
                    .align(Alignment.Start),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .size(48.dp)
                        .clickable {
                            visible()
                        },
                    painter = painterResource(id = R.drawable.back),
                    tint = onPrimary,
                    contentDescription = ""
                )
                Text(
                    modifier = Modifier
                        .padding(start = 4.dp),
                    fontWeight = FontWeight.Bold,
                    text = stringResource(R.string.language_selected), style = TextStyle(
                        color = Primary, fontSize = 24.sp
                    )
                )
            }

            LazyColumn(
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 16.dp)
            ) {
                items(listLanguage) { language ->
                    if (USER_LANGUAGE == "fa") {
                        tickFa = true
                        tickEn = false
                    } else {
                        tickEn = true
                        tickFa = false
                    }
                    when (language) {
                        "فارسی" -> {
                            ItemLanguage(language = language, tickFa) {
                                USER_LANGUAGE = "fa"
                                tickEn = false
                                tickFa = true
                                visible()
                                Toast.makeText(
                                    context,
                                    "زبان روی فارسی تنظیم شد",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }

                        "English" -> {
                            ItemLanguage(language = language, tickEn) {
                                USER_LANGUAGE = "en"
                                tickFa = false
                                tickEn = true
                                visible()
                                Toast.makeText(
                                    context,
                                    "The language is set to English",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ItemLanguage(
    language: String,
    visible: Boolean,
    onclick: () -> Unit,
) {
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
            Row(
                modifier = Modifier
                    .padding(start = 16.dp, top = 8.dp, bottom = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (visible) {
                    Icon(
                        modifier = Modifier
                            .size(48.dp),
                        painter = painterResource(id = R.drawable.select),
                        tint = onPrimary,
                        contentDescription = ""
                    )
                }
                Text(
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(16.dp),
                    text = language,
                    style = TextStyle(
                        textAlign = TextAlign.Center,
                        fontSize = 26.sp,
                        color = Black
                    )
                )
            }
        }
    }
}