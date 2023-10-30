package com.mati.miweather.data.network

import com.mati.miweather.data.model.CityForecast
import com.mati.miweather.data.model.CitysStatus
import com.mati.miweather.util.API_KEY
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("data/2.5/weather")
    fun getWeather(
        @Query("q") location: String,
        @Query("appid") apiKey: String? = API_KEY,
        @Query("lang") language: String? = "fa"
    ): Call<CitysStatus>

    @GET("data/2.5/forecast")
    fun getCityForecast(
        @Query("q") location: String,
        @Query("appid") apiKey: String? = API_KEY,
        @Query("lang") language: String? = "fa"
    ): Call<CityForecast>

}