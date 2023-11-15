package com.mati.miweather.ui.feature.Setting

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.mati.mimovies.utils.NavigationItem
import com.mati.miweather.R
import com.mati.miweather.data.model.SettingItem
import com.mati.miweather.ui.feature.MainViewModel
import com.mati.miweather.ui.feature.Setting.DialogsItem.DialogLanguage
import com.mati.miweather.ui.feature.Setting.DialogsItem.DialogTheme
import com.mati.miweather.ui.feature.Setting.DialogsItem.Success
import com.mati.miweather.ui.theme.Transparent
import com.mati.miweather.util.BackgroundDialog
import com.mati.miweather.util.USER_LANGUAGE
import com.mati.miweather.util.USER_THEME

@Composable
fun SettingScreen(
    viewModel: MainViewModel,
    navHostController: NavHostController,
) {

    val systemUiController = rememberSystemUiController()
    systemUiController.isNavigationBarVisible = false

    val backgroundColor = Brush.verticalGradient(
        0.0f to MaterialTheme.colorScheme.primary,
        1.0f to MaterialTheme.colorScheme.secondary,
        startY = 0.0f,
        endY = 800.0f
    )

    var visibleLanguage by remember { mutableStateOf(false) }
    var visibleTheme by remember { mutableStateOf(false) }
    var visibleSuccess by remember { mutableStateOf(false) }

    val item = arrayListOf(
        SettingItem(0, R.drawable.language, stringResource(R.string.language)),
        SettingItem(1, R.drawable.service, stringResource(R.string.services)),
        SettingItem(2, R.drawable.theme, stringResource(R.string.theme)),
        SettingItem(3, R.drawable.unit, stringResource(R.string.unit)),
    )

    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = backgroundColor),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .padding(start = 16.dp, top = 8.dp, bottom = 4.dp)
                    .align(Alignment.Start), verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .size(48.dp)
                        .clickable {
                            navHostController.navigate(NavigationItem.MainScreen.route) {
                                popUpTo(NavigationItem.SettingScreen.route) {
                                    inclusive = true
                                }
                            }
                        },
                    painter = painterResource(id = R.drawable.back),
                    tint = MaterialTheme.colorScheme.onPrimary,
                    contentDescription = ""
                )
                Text(
                    modifier = Modifier.padding(start = 4.dp),
                    fontWeight = FontWeight.Bold,
                    text = stringResource(R.string.setting),
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.surface, fontSize = 24.sp
                    )
                )
            }
            LazyVerticalGrid(
                columns = GridCells.Fixed(count = 2)
            ) {
                items(
                    items = item,
                ) {
                    ItemSetting(it) {
                        when (it.id) {
                            0 -> visibleLanguage = true
                            2 -> visibleTheme = true
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(14.dp))
            ItemAbout() {}
        }

        if (visibleLanguage) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        BackgroundDialog
                    )
                    .clickable {
                        visibleLanguage = false
                    }) {
                DialogLanguage({
                    visibleSuccess = true
                    visibleLanguage = false
                }) {
                    visibleLanguage = false
                }
            }
        }

        if (visibleTheme) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        BackgroundDialog
                    )
                    .clickable {
                        visibleTheme = false
                    }) {
                DialogTheme({
                    visibleSuccess = true
                    visibleTheme = false
                }) {
                    visibleTheme = false
                }
            }
        }

        if (visibleSuccess) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        BackgroundDialog
                    )
                    .clickable {
                        visibleSuccess = false
                        viewModel.languageSave(USER_LANGUAGE)
                        viewModel.themeSave(USER_THEME)
                    }
            ) {
                Success {
                    visibleSuccess = false
                    viewModel.languageSave(USER_LANGUAGE)
                    viewModel.themeSave(USER_THEME)
                }
            }
        }
    }
}

@Composable
fun ItemSetting(result: SettingItem, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .size(60.dp, 150.dp)
            .background(
                Transparent
            )
            .padding(16.dp), elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ), shape = RoundedCornerShape(14.dp)
    ) {
        Column(modifier = Modifier
            .clickable {
                onClick()
            }
            .background(MaterialTheme.colorScheme.onTertiary)
            .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Icon(
                modifier = Modifier.size(60.dp),
                tint = MaterialTheme.colorScheme.onPrimary,
                painter = painterResource(id = result.icon),
                contentDescription = result.name
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                fontWeight = FontWeight.Bold,
                text = result.name,
                style = TextStyle(
                    color = MaterialTheme.colorScheme.surface, textAlign = TextAlign.Center
                )
            )
        }
    }
}

@Composable
fun ItemAbout(onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .background(
                Transparent
            )
            .padding(16.dp), elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ), shape = RoundedCornerShape(14.dp)
    ) {
        Column(modifier = Modifier
            .clickable {
                onClick()
            }
            .background(MaterialTheme.colorScheme.onTertiary)
            .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Icon(
                modifier = Modifier.size(52.dp),
                tint = MaterialTheme.colorScheme.onPrimary,
                painter = painterResource(id = R.drawable.about),
                contentDescription = stringResource(R.string.about)
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                fontWeight = FontWeight.Bold,
                text = stringResource(R.string.about),
                style = TextStyle(
                    color = MaterialTheme.colorScheme.surface, textAlign = TextAlign.Center
                )
            )
        }
    }
}