@file:OptIn(ExperimentalMaterial3Api::class)

package com.mati.miweather.ui.feature.SelectCity

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.mati.miweather.ui.feature.MainViewModel
import com.mati.miweather.ui.theme.Black
import com.mati.miweather.ui.theme.Transparent
import com.mati.miweather.ui.theme.White

@Composable
fun SelectCity(viewModel: MainViewModel, visible: () -> Unit) {

    val backgroundColor = Brush.verticalGradient(
        0.0f to MaterialTheme.colorScheme.primary,
        1.0f to MaterialTheme.colorScheme.secondary,
        startY = 0.0f,
        endY = 800.0f
    )

    val searchBox = remember { mutableStateOf("") }

    val cities =
        listOf(
            stringResource(R.string.tehran),
            stringResource(R.string.Qom),
            stringResource(R.string.esfahan),
            stringResource(R.string.hamedan),
            stringResource(R.string.mashhad),
            stringResource(R.string.karaj),
            stringResource(R.string.yazd),
            stringResource(R.string.shiraz),
            stringResource(R.string.sari),
            stringResource(R.string.ardabil),
            stringResource(R.string.sirjan),
            stringResource(R.string.shahrKord),
            stringResource(R.string.rasht),
            stringResource(R.string.gorgan),
            stringResource(R.string.arak),
            stringResource(R.string.Yasuj),
            stringResource(R.string.tabriz),
            stringResource(R.string.kerman),
            stringResource(R.string.kashan),
            stringResource(R.string.Kermanshah),
            stringResource(R.string.golestan),
            stringResource(R.string.zahedan),
            stringResource(R.string.hormozgan),
            stringResource(R.string.ahvaz),
            stringResource(R.string.sanandaj),
            stringResource(R.string.bandarAbbas)
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
                    text = stringResource(R.string.city_selected), style = TextStyle(
                        color = MaterialTheme.colorScheme.surface, fontSize = 24.sp
                    )
                )
            }

            searchBox(searchBox) {

            }

            LazyColumn(
                modifier = Modifier
                    .padding(bottom = 16.dp)
            ) {
                items(cities) { city ->
                    CityNameItem(city, "IRAN") {
                        visible()
                        viewModel.newData(city)
                    }
                }
            }
        }
    }
}

@Composable
fun CityNameItem(cityName: String, country: String, onclick: () -> Unit) {
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
        Column(
            modifier = Modifier
                .padding(start = 16.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    tint = MaterialTheme.colorScheme.onSurface,
                    contentDescription = ""
                )
                Text(
                    textAlign = TextAlign.Center,
                    text = cityName,
                    style = TextStyle(
                        fontSize = 26.sp,
                        color = Black
                    )
                )
            }
            Text(
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 6.dp, bottom = 6.dp),
                text = country,
                style = TextStyle(
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
            )
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun searchBox(searchBox: MutableState<String>, clickable: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Transparent
            )
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 2.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.onTertiary),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                tint = MaterialTheme.colorScheme.onSurface,
                contentDescription = "",
                modifier = Modifier
                    .padding(start = 8.dp)
                    .clickable {
                        clickable()
                    }
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = MaterialTheme.colorScheme.onSurface,
                    disabledTextColor = Transparent,
                    focusedIndicatorColor = Transparent,
                    unfocusedIndicatorColor = Transparent,
                    containerColor = Transparent,
                    disabledIndicatorColor = Transparent
                ),
                singleLine = true,
                value = searchBox.value, onValueChange = {
                    searchBox.value = it
                })
        }
    }
}
