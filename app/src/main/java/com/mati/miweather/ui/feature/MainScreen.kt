package com.mati.miweather.ui.feature

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.mati.mimovies.utils.NavigationItem
import com.mati.miweather.R
import com.mati.miweather.ui.feature.Error.ErrorPage
import com.mati.miweather.ui.feature.Forcast.ListForecastItem
import com.mati.miweather.ui.feature.Header.Header
import com.mati.miweather.ui.feature.SelectCity.SelectCity
import com.mati.miweather.ui.feature.Splash.SplashScreen
import com.mati.miweather.ui.feature.StatusBar.StatusBar
import com.mati.miweather.util.BackgroundDialog
import com.mati.miweather.util.NetworkChecker
import com.mati.miweather.util.USER_LANGUAGE

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

    val backgroundColor = Brush.verticalGradient(
        0.0f to MaterialTheme.colorScheme.primary,
        1.0f to MaterialTheme.colorScheme.secondary,
        startY = 0.0f,
        endY = 800.0f
    )

    val context = LocalContext.current
    if (NetworkChecker(context).isInternetConnected) {
        if (response.isLoading || responseForecast.isLoading) {
            SplashScreen()
        } else if (response.error != null || responseForecast.error != null) {
            ErrorPage(response.error, responseForecast.error) {
                navHostController.navigate(NavigationItem.MainScreen.route) {
                    viewModel.getData()
                    popUpTo(NavigationItem.MainScreen.route) {
                        inclusive = true
                    }
                }
            }
        }
    } else {
        ErrorPage(stringResource(R.string.check_connection), "") {
            navHostController.navigate(NavigationItem.MainScreen.route) {
                viewModel.getData()
                popUpTo(NavigationItem.MainScreen.route) {
                    inclusive = true
                }
            }
        }
    }
    val scrollState = rememberScrollState()
    if (data?.name != null && responseForecast.data?.list != null) {
        response.isLoading = false
        responseForecast.isLoading = false
        Surface {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .background(brush = backgroundColor),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Header(response = data, {
                    visibleDialog = true
                }) {
                    navHostController.navigate(NavigationItem.SettingScreen.route) {
                    }
                }
                StatusBar(response = data)
                TitleList(stringResource(R.string.forecast), stringResource(R.string.five_day))
                LazyRow(
                    modifier = Modifier.padding(start = 4.dp)
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
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(
                        BackgroundDialog
                    )
                    .clickable {
                        visibleDialog = false
                    })
                SelectCity(viewModel) {
                    visibleDialog = false
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
    ) {
        if (USER_LANGUAGE == "en") {
            Text(
                modifier = Modifier.padding(start = 8.dp),
                fontWeight = FontWeight.Bold,
                text = text,
                style = TextStyle(
                    color = MaterialTheme.colorScheme.surface, fontSize = 24.sp
                )
            )
            TextButton(onClick = { /*TODO*/ }) {
                Text(
                    text = action, style = TextStyle(
                        color = MaterialTheme.colorScheme.onPrimary, fontSize = 18.sp
                    )
                )
            }
        } else {
            TextButton(onClick = { /*TODO*/ }) {
                Text(
                    text = action, style = TextStyle(
                        color = MaterialTheme.colorScheme.onPrimary, fontSize = 18.sp
                    )
                )
            }
            Text(
                modifier = Modifier.padding(end = 8.dp),
                fontWeight = FontWeight.Bold,
                text = text,
                style = TextStyle(
                    color = MaterialTheme.colorScheme.surface, fontSize = 24.sp
                )
            )
        }
    }
}
