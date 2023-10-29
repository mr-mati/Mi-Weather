package com.mati.miweather.ui.feature

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mati.miweather.data.model.CityForecast
import com.mati.miweather.data.model.CitysStatus
import com.mati.miweather.data.repository.RepositoryImpl
import com.mati.miweather.util.API_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Timer
import javax.inject.Inject
import kotlin.concurrent.timerTask

@HiltViewModel
class MainViewModel @Inject constructor(repositoryImpl: RepositoryImpl) : ViewModel() {

    private val _city: MutableState<CityState> = mutableStateOf(CityState())
    val city: State<CityState> = _city

    private val _cityForecast: MutableState<ForecastState> = mutableStateOf(ForecastState())
    val cityForecast: State<ForecastState> = _cityForecast

    val repository = repositoryImpl
    fun newData(cityName: String) {
        repository.newData(cityName)
        viewModelScope.launch {
            Timer().schedule(timerTask {
                _city.value = repository.cityResponse!!
                _cityForecast.value = repository.forecastResponse!!
            }, 1000)
        }
    }

    init {
        viewModelScope.launch {
            Timer().schedule(timerTask {
                _city.value = repositoryImpl.cityResponse!!
                _cityForecast.value = repositoryImpl.forecastResponse!!
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