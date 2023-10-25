package com.mati.mimovies.utils

sealed class MovieNavigationItems(val route:String){

    object MainScreen : MovieNavigationItems("mainScreen")

}
