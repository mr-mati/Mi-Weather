package com.mati.miweather.data.repository

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import com.mati.miweather.data.model.CityForecast
import com.mati.miweather.data.model.CitysStatus
import com.mati.miweather.data.network.ApiService
import com.mati.miweather.ui.feature.CityState
import com.mati.miweather.ui.feature.ForecastState
import com.mati.miweather.util.API_KEY
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryImpl(apiService: ApiService) {

    var location = "tehran"
    val apiKey = API_KEY
    val language = "fa"

    var cityResponse: CityState? = null
    var forecastResponse: ForecastState? = null

    private val apiService = apiService
    fun newData(cityName: String) {

        location = cityName
        apiService.getWeather(location, apiKey, language).enqueue(object : Callback<CitysStatus> {
            override fun onResponse(call: Call<CitysStatus>, response: Response<CitysStatus>) {
                cityResponse = CityState(data = response.body())
            }

            override fun onFailure(call: Call<CitysStatus>, t: Throwable) {
                cityResponse = CityState(error = t.message.toString())
            }
        })

        apiService.getCityForecast(location, apiKey, language)
            .enqueue(object : Callback<CityForecast> {
                override fun onResponse(
                    call: Call<CityForecast>,
                    response: Response<CityForecast>,
                ) {
                    forecastResponse = ForecastState(data = response.body())
                }

                override fun onFailure(call: Call<CityForecast>, t: Throwable) {
                    forecastResponse = ForecastState(error = t.message.toString())
                }
            })

    }

    init {
        apiService.getWeather(location, apiKey, language).enqueue(object : Callback<CitysStatus> {
            override fun onResponse(call: Call<CitysStatus>, response: Response<CitysStatus>) {
                cityResponse = CityState(data = response.body())
            }

            override fun onFailure(call: Call<CitysStatus>, t: Throwable) {
                cityResponse = CityState(error = t.message.toString())
            }
        })

        apiService.getCityForecast(location, apiKey, language)
            .enqueue(object : Callback<CityForecast> {
                override fun onResponse(
                    call: Call<CityForecast>,
                    response: Response<CityForecast>,
                ) {
                    forecastResponse = ForecastState(data = response.body())
                }

                override fun onFailure(call: Call<CityForecast>, t: Throwable) {
                    forecastResponse = ForecastState(error = t.message.toString())
                }
            })

    }

}