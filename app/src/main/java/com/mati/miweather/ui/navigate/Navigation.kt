package com.mati.miweather.ui.navigate

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mati.mimovies.utils.NavigationItem
import com.mati.miweather.ui.feature.MainScreen
import com.mati.miweather.ui.feature.MainViewModel
import com.mati.miweather.ui.feature.Setting.SettingScreen

@Composable
fun Navigation(viewModel: MainViewModel) {
    val navHostController = rememberNavController()
    NavHost(
        navController = navHostController,
        startDestination = NavigationItem.MainScreen.route
    ) {
        composable(NavigationItem.MainScreen.route) {
            MainScreen(viewModel, navHostController)
        }
        composable(NavigationItem.SettingScreen.route) {
            SettingScreen(viewModel, navHostController)
        }
    }

}