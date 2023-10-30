package com.mati.miweather.ui.feature

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.mati.mimovies.utils.NavigationItem
import com.mati.miweather.ui.feature.Forcast.ListForecastItem
import com.mati.miweather.ui.feature.Header.Header
import com.mati.miweather.ui.feature.SelectCity.SelectCity
import com.mati.miweather.ui.feature.StatusBar.StatusBar
import com.mati.miweather.ui.theme.Background
import com.mati.miweather.ui.theme.Background1
import com.mati.miweather.ui.theme.Primary
import com.mati.miweather.ui.theme.onPrimary

@SuppressLint("UnrememberedMutableState")
@Composable
fun MainScreen(
    viewModel: MainViewModel,
    navHostController: NavHostController,
) {
    val response = viewModel.city.value
    val data = viewModel.city.value.data

    val responseForecast = viewModel.cityForecast.value
    val listForecast = responseForecast.data?.list

    var visibleDialog by remember { mutableStateOf(false) }

    val systemUiController = rememberSystemUiController()
    systemUiController.isNavigationBarVisible = false
    systemUiController.setNavigationBarColor(Background1)
    systemUiController.setStatusBarColor(Background1)

    val backgroundColor = Brush.verticalGradient(
        0.0f to Background1, 1.0f to Background, startY = 0.0f, endY = 800.0f
    )

    if (response.isLoading || responseForecast.isLoading) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = backgroundColor),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                color = Color.Blue, // You can set the color of the CircularProgressIndicator
                strokeWidth = 5.dp, // You can adjust the stroke width
            )
        }

        if (data?.name != null && responseForecast.data?.list != null) {
            response.isLoading = false
            responseForecast.isLoading = false
            Surface {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(brush = backgroundColor),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Header(response = data) {
                        visibleDialog = true
                    }
                    StatusBar(response = data)
                    TitleList("Forecast", "5 Day")
                    LazyRow(
                        modifier = Modifier
                            .padding(start = 4.dp)
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
                if (visibleDialog) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                color = Color(0.5f, 0.5f, 0.5f, 0.5f)
                            )
                            .clickable {
                                visibleDialog = false
                            }
                    )
                    SelectCity(viewModel) {
                        visibleDialog = false
                    }
                }
            }
        }
    } else if (response.error.isNotEmpty() || responseForecast.error.isNotEmpty()) {
        if (data?.name != null && responseForecast.data?.list != null) {
            response.isLoading = false
            responseForecast.isLoading = false
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(brush = backgroundColor)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Error",
                    fontSize = 24.sp,
                    color = Color.Red
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = response.error,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = responseForecast.error,
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = {
                        navHostController.navigate(NavigationItem.MainScreen.route) {
                            popUpTo(NavigationItem.MainScreen.route) {
                                inclusive = true
                            }
                        }
                    },
                    contentPadding = PaddingValues(16.dp)
                ) {
                    Text(text = "Retry")
                }
            }
        }
    }
}

@Composable
fun TitleList(text: String, action: String) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 18.dp, start = 8.dp, top = 8.dp, bottom = 4.dp)
    )
    {
        Text(
            modifier = Modifier
                .padding(start = 8.dp),
            fontWeight = FontWeight.Bold,
            text = text, style = TextStyle(
                color = Primary, fontSize = 24.sp
            )
        )
        TextButton(onClick = { /*TODO*/ }) {
            Text(
                text = action, style = TextStyle(
                    color = onPrimary,
                    fontSize = 18.sp
                )
            )
        }
    }
}
