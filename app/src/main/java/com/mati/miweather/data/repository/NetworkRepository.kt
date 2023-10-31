package com.mati.miweather.data.repository

import com.mati.miweather.data.model.CityForecast
import com.mati.miweather.data.model.CitysStatus
import com.mati.miweather.data.network.ApiService
import com.mati.miweather.ui.feature.CityState
import com.mati.miweather.ui.feature.ForecastState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NetworkRepository(apiService: ApiService) {

    var cityResponse: CitysStatus? = null
    var forecastResponse: CityForecast? = null

    var cityError: String? = null
    var ForecastError: String? = null

    private val apiService = apiService

    fun newData(cityName: String) {
        val location = cityName
        apiService.getWeather(location).enqueue(object : Callback<CitysStatus> {
            override fun onResponse(call: Call<CitysStatus>, response: Response<CitysStatus>) {
                cityResponse = response.body()
                CityState(isLoading = false)
            }

            override fun onFailure(call: Call<CitysStatus>, t: Throwable) {
                cityError = t.message.toString()
            }
        })

        apiService.getCityForecast(location)
            .enqueue(object : Callback<CityForecast> {
                override fun onResponse(
                    call: Call<CityForecast>,
                    response: Response<CityForecast>,
                ) {
                    forecastResponse = response.body()
                    ForecastState(isLoading = false)
                }

                override fun onFailure(call: Call<CityForecast>, t: Throwable) {
                    ForecastError = t.message.toString()
                }
            })

    }

}