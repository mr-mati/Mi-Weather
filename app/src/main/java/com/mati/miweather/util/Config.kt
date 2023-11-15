package com.mati.miweather.util

import androidx.compose.ui.graphics.Color

const val BASE_URL = "https://api.openweathermap.org/"
const val API_KEY = "489c59265fb912e0096ba30d3fa6e79f"
const val BASE_IMAGE_URL = "https://openweathermap.org/img/wn/"

var USER_LANGUAGE = "USER_LANGUAGE"
const val ENGLISH = "en"
const val PERSIAN = "fa"

var USER_THEME = "dark"


val BackgroundDialog = if (USER_THEME == "Dark" && USER_THEME == "تاریک") {
    Color(0.702f, 0.898f, 0.988f, 0.5f)
} else {
    Color(0.169f, 0.176f, 0.188f, 0.5f)
}

const val PREFERENCE_NAME = "mi_preferences"
const val PREFERENCE_CITY_KEY = "city_name_key"
const val PREFERENCE_LANGUAGE_KEY = "language_key"
const val PREFERENCE_THEME_KEY = "THEME_key"