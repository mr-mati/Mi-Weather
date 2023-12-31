@file:OptIn(ExperimentalMaterial3Api::class)

package com.mati.miweather.ui.feature.Setting.DialogsItem

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mati.miweather.R
import com.mati.miweather.ui.theme.Black
import com.mati.miweather.ui.theme.Transparent
import com.mati.miweather.ui.theme.White
import com.mati.miweather.util.USER_LANGUAGE

@Composable
fun DialogLanguage(
    select: () -> Unit,
    visible: () -> Unit,
) {

    val backgroundColor = Brush.verticalGradient(
        0.0f to MaterialTheme.colorScheme.primary,
        1.0f to MaterialTheme.colorScheme.secondary,
        startY = 0.0f,
        endY = 800.0f
    )

    val listLanguage =
        listOf(
            "فارسی",
            "English"
        )

    var tickEn by remember { mutableStateOf(false) }
    var tickFa by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier
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
                        tint = MaterialTheme.colorScheme.onPrimary,
                        contentDescription = ""
                    )
                    Text(
                        modifier = Modifier
                            .padding(start = 4.dp),
                        fontWeight = FontWeight.Bold,
                        text = stringResource(R.string.language), style = TextStyle(
                            color = MaterialTheme.colorScheme.surface, fontSize = 24.sp
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
                                    if (USER_LANGUAGE != "fa") {
                                        USER_LANGUAGE = "fa"
                                        tickEn = false
                                        tickFa = true
                                        select()
                                    }
                                }
                            }

                            "English" -> {
                                ItemLanguage(language = language, tickEn) {
                                    if (USER_LANGUAGE != "en") {
                                        USER_LANGUAGE = "en"
                                        tickFa = false
                                        tickEn = true
                                        select()
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@ExperimentalMaterial3Api
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
            }
            .padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = White,
        ),
        onClick = {
            onclick()
        },
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
                        tint = MaterialTheme.colorScheme.primary,
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

/*
@Composable
fun SuccessDialog(title: String, subTitle: String, visibleSuccess: MutableState<Boolean>) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(
            color = Color(0.9f, 0.9f, 0.9f, 0.8f)
        )
        .clickable {
            visibleSuccess = false
        }) {
        Success(title, subTitle) {
            visibleSuccess = false
        }
    }
}*/
