package com.mati.miweather.ui.feature

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mati.miweather.data.model.CityForecast
import com.mati.miweather.data.model.CitysStatus
import com.mati.miweather.data.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(apiService: ApiService) : ViewModel() {

    private val _city: MutableState<CityState> = mutableStateOf(CityState())
    val city: State<CityState> = _city

    private val _cityForecast: MutableState<ForecastState> = mutableStateOf(ForecastState())
    val cityForecast: State<ForecastState> = _cityForecast

    private val apiService = apiService
    fun newCityName(newName: String) {
        val location = newName
        val apiKey = "489c59265fb912e0096ba30d3fa6e79f"
        val language = "fa"

        apiService.getWeather(location, apiKey, language).enqueue(object : Callback<CitysStatus> {
            override fun onResponse(call: Call<CitysStatus>, response: Response<CitysStatus>) {
                viewModelScope.launch {
                    _city.value = CityState(data = response.body())
                }
            }

            override fun onFailure(call: Call<CitysStatus>, t: Throwable) {
                _city.value = CityState(error = t.message.toString())
            }

        })

        apiService.getCityForecast(location, apiKey, language)
            .enqueue(object : Callback<CityForecast> {
                override fun onResponse(
                    call: Call<CityForecast>,
                    response: Response<CityForecast>,
                ) {
                    viewModelScope.launch {
                        _cityForecast.value = ForecastState(data = response.body())
                    }
                }

                override fun onFailure(call: Call<CityForecast>, t: Throwable) {
                    _cityForecast.value = ForecastState(error = t.message.toString())
                }
            })

    }

    val location = "Tehran"
    val apiKey = "489c59265fb912e0096ba30d3fa6e79f"
    val language = "fa"

    init {
        apiService.getWeather(location, apiKey, language).enqueue(object : Callback<CitysStatus> {
            override fun onResponse(call: Call<CitysStatus>, response: Response<CitysStatus>) {
                viewModelScope.launch {
                    _city.value = CityState(data = response.body())
                }
            }

            override fun onFailure(call: Call<CitysStatus>, t: Throwable) {
                _city.value = CityState(error = t.message.toString())
            }

        })
    }

    init {
        apiService.getCityForecast(location, apiKey, language)
            .enqueue(object : Callback<CityForecast> {
                override fun onResponse(
                    call: Call<CityForecast>,
                    response: Response<CityForecast>,
                ) {
                    viewModelScope.launch {
                        _cityForecast.value = ForecastState(data = response.body())
                    }
                }

                override fun onFailure(call: Call<CityForecast>, t: Throwable) {
                    _cityForecast.value = ForecastState(error = t.message.toString())
                }
            })
    }

}

data class CityState(
    val data: CitysStatus? = null,
    val error: String = " ",
    val isLoading: Boolean = false,
)

data class ForecastState(
    val data: CityForecast? = null,
    val error: String = " ",
    val isLoading: Boolean = false,
)