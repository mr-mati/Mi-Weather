package com.mati.miweather

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.mati.miweather.ui.feature.MainViewModel
import com.mati.miweather.ui.navigate.Navigation
import com.mati.miweather.ui.theme.MiWeatherTheme
import com.mati.miweather.util.LocaleUtil
import com.mati.miweather.util.USER_LANGUAGE
import com.mati.miweather.util.USER_THEME
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel: MainViewModel by viewModels()

    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getData()
        setContent {
            MiWeatherTheme {

                val backgroundColor = Brush.verticalGradient(
                    0.0f to MaterialTheme.colorScheme.primary,
                    1.0f to MaterialTheme.colorScheme.secondary,
                    startY = 0.0f,
                    endY = 800.0f
                )

                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(brush = backgroundColor),
                ) {
                    LocaleUtil.setLocale(LocalContext.current, USER_LANGUAGE)
                    Navigation(viewModel)
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.getData()
    }

    override fun onRestart() {
        super.onRestart()
        viewModel.getData()
    }

    override fun onPause() {
        super.onPause()
        viewModel.languageSave(USER_LANGUAGE)
        viewModel.themeSave(USER_THEME)
    }

    override fun onStop() {
        super.onStop()
        viewModel.languageSave(USER_LANGUAGE)
        viewModel.themeSave(USER_THEME)
    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello ${name}!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MiWeatherTheme {
        Greeting("Android")
    }
}