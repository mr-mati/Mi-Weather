package com.mati.miweather.data.model

data class CitysStatus(
    val base: String,
    val clouds: Clouds,
    val cod: Int,
    val dt: Int,
    val id: Int,
    val main: Main,
    val name: String,
    val sys: Sys,
    val timezone: Int,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind,
) {
    data class Clouds(
        val all: Int,
    )

    data class Main(
        val feels_like: Double,
        val grnd_level: Int,
        val humidity: Int,
        val pressure: Int,
        val sea_level: Int,
        val temp: Double,
        val temp_max: Double,
        val temp_min: Double,
    )

    data class Sys(
        val country: String,
        val id: Int,
        val sunrise: Int,
        val sunset: Int,
        val type: Int,
    )

    data class Weather(
        val description: String,
        val icon: String,
        val id: Int,
        val main: String,
    )

    data class Wind(
        val deg: Int,
        val gust: Double,
        val speed: Double,
    )
}

data class CitysStatusUi(
    val id: Int,
    val main: CitysStatus.Main,
    val name: String,
)