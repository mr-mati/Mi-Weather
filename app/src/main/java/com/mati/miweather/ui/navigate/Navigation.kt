package com.mati.miweather.ui.navigate

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mati.mimovies.utils.MovieNavigationItems
import com.mati.miweather.data.model.CitysStatus
import com.mati.miweather.ui.feature.MainScreen
import com.mati.miweather.ui.feature.MainViewModel

@Composable
fun Navigation(viewModel: MainViewModel) {
    val navHostController = rememberNavController()
    NavHost(
        navController = navHostController,
        startDestination = MovieNavigationItems.MainScreen.route
    ) {
        composable(MovieNavigationItems.MainScreen.route) {
            MainScreen(viewModel)
        }
    }

}