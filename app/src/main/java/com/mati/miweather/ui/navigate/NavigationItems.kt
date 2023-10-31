package com.mati.mimovies.utils

sealed class NavigationItem(val route: String) {

    object MainScreen : NavigationItem("mainScreen")

}
