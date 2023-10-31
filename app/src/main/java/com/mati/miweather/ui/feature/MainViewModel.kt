package com.mati.miweather.ui.feature

import android.os.Handler
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mati.miweather.data.model.CityForecast
import com.mati.miweather.data.model.CitysStatus
import com.mati.miweather.data.repository.NetworkRepository
import com.mati.miweather.util.NetworkChecker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Timer
import javax.inject.Inject
import kotlin.concurrent.timerTask

@HiltViewModel
class MainViewModel @Inject constructor(repositoryImpl: NetworkRepository) : ViewModel() {

    val repository = repositoryImpl

    private val _city: MutableState<CityState> = mutableStateOf(CityState())
    val city: State<CityState> = _city

    private val _cityForecast: MutableState<ForecastState> = mutableStateOf(ForecastState())
    val cityForecast: State<ForecastState> = _cityForecast

    fun newData(cityName: String) {
        repository.newData(cityName)
        viewModelScope.launch {
            Timer().schedule(timerTask {
                _city.value = CityState(repository.cityResponse)
                _cityForecast.value = ForecastState(repository.forecastResponse)
            }, 1000)
        }
    }

    init {
        repository.newData("Tehran")
        viewModelScope.launch {
            Handler().postDelayed({
                _city.value = CityState(data = repository.cityResponse)
                _cityForecast.value = ForecastState(data = repository.forecastResponse)
                if (repository.cityError != null || repository.ForecastError != null) {
                    _city.value = CityState(error = repository.cityError!!)
                    _cityForecast.value = ForecastState(error = repository.ForecastError!!)
                }
            }, 2000)
        }
    }

}

data class CityState(
    val data: CitysStatus? = null,
    val error: String = " ",
    var isLoading: Boolean = true,
)

data class ForecastState(
    val data: CityForecast? = null,
    val error: String = " ",
    var isLoading: Boolean = true,
)