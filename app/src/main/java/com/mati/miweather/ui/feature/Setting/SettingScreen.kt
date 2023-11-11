package com.mati.miweather.ui.feature.Setting

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.mati.miweather.R
import com.mati.miweather.data.model.SettingItem
import com.mati.miweather.data.repository.DataStoreRepository
import com.mati.miweather.ui.feature.MainViewModel
import com.mati.miweather.ui.theme.Background
import com.mati.miweather.ui.theme.Background1
import com.mati.miweather.ui.theme.Primary
import com.mati.miweather.ui.theme.Transparent
import com.mati.miweather.ui.theme.White
import com.mati.miweather.ui.theme.onPrimary
import javax.inject.Inject

@Composable
fun SettingScreen(
    viewModel: MainViewModel,
    navHostController: NavHostController,
) {

    val systemUiController = rememberSystemUiController()
    systemUiController.isNavigationBarVisible = false
    systemUiController.setNavigationBarColor(Background1)
    systemUiController.setStatusBarColor(Background1)

    val backgroundColor = Brush.verticalGradient(
        0.0f to Background1, 1.0f to Background, startY = 0.0f, endY = 800.0f
    )

    val item = arrayListOf(
        SettingItem(R.drawable.language, stringResource(R.string.language)),
        SettingItem(R.drawable.service, stringResource(R.string.services)),
        SettingItem(R.drawable.theme, stringResource(R.string.theme)),
        SettingItem(R.drawable.unit, stringResource(R.string.unit)),
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = backgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .padding(start = 16.dp, top = 8.dp, bottom = 4.dp)
                .align(Alignment.Start),
            fontWeight = FontWeight.Bold,
            text = stringResource(R.string.setting), style = TextStyle(
                color = Primary, fontSize = 24.sp
            )
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(count = 2)
        ) {
            items(
                items = item,
            ) {
                ItemSetting(it){
                }
            }
        }
        Spacer(modifier = Modifier.height(14.dp))
        ItemAbout(){
        }
    }
}

@Composable
fun ItemSetting(result: SettingItem, onClick : () -> Unit) {
    Card(
        modifier = Modifier
            .size(60.dp, 150.dp)
            .background(
                Transparent
            )
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        shape = RoundedCornerShape(14.dp)
    ) {
        Column(
            modifier = Modifier
                .clickable {
                    onClick()
                }
                .background(White)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier
                    .size(60.dp),
                tint = onPrimary,
                painter = painterResource(id = result.icon), contentDescription = result.name
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                fontWeight = FontWeight.Bold,
                text = result.name, style = TextStyle(
                    color = Primary,
                    textAlign = TextAlign.Center
                )
            )
        }
    }
}

@Composable
fun ItemAbout(onClick : () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .background(
                Transparent
            )
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        shape = RoundedCornerShape(14.dp)
    ) {
        Column(
            modifier = Modifier
                .clickable {
                    onClick()
                }
                .background(White)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier
                    .size(52.dp),
                tint = onPrimary,
                painter = painterResource(id = R.drawable.about),
                contentDescription = stringResource(R.string.about)
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                fontWeight = FontWeight.Bold,
                text = stringResource(R.string.about), style = TextStyle(
                    color = Primary,
                    textAlign = TextAlign.Center
                )
            )
        }
    }
}