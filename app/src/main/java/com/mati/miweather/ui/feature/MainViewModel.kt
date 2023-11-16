package com.mati.miweather.ui.feature

import android.os.Handler
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mati.miweather.data.model.CityForecast
import com.mati.miweather.data.model.CitysStatus
import com.mati.miweather.data.repository.DataStoreRepository
import com.mati.miweather.data.repository.NetworkRepository
import com.mati.miweather.util.USER_LANGUAGE
import com.mati.miweather.util.USER_THEME
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Timer
import javax.inject.Inject
import kotlin.concurrent.timerTask

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: NetworkRepository,
    private val dataStoreRepository: DataStoreRepository,
) : ViewModel() {

    private val _city: MutableState<CityState> = mutableStateOf(CityState())
    val city: State<CityState> = _city

    private val _cityForecast: MutableState<ForecastState> = mutableStateOf(ForecastState())
    val cityForecast: State<ForecastState> = _cityForecast

    fun newData(cityName: String) {
        viewModelScope.launch {
            repository.newData(cityName, languageRead())
            cityNameSave(cityName)
            Timer().schedule(timerTask {
                _city.value = CityState(repository.cityResponse)
                _cityForecast.value = ForecastState(repository.forecastResponse)
            }, 1000)
        }
    }

    fun getData() {
        viewModelScope.launch {
            repository.newData(cityNameRead(), languageRead())
            USER_LANGUAGE = languageRead()
            USER_THEME = themeRead()
            Handler().postDelayed({
                if (repository.cityResponse?.id != null || repository.cityResponse?.id != null) {
                    _city.value = CityState(data = repository.cityResponse)
                    _cityForecast.value = ForecastState(data = repository.forecastResponse)
                    CityState(isLoading = false)
                    ForecastState(isLoading = false)
                }
                if (repository.cityError != null || repository.ForecastError != null) {
                    _city.value = CityState(error = repository.cityError!!)
                    _cityForecast.value = ForecastState(error = repository.ForecastError!!)
                }
            }, 2000)
        }
    }

    private suspend fun cityNameSave(cityName: String) {
        dataStoreRepository.saveCityName(cityName)
    }

    private suspend fun cityNameRead(): String = dataStoreRepository.readCityName()


    fun languageSave(language: String) {
        viewModelScope.launch {
            dataStoreRepository.saveLanguage(language)
        }
    }

    suspend fun languageRead(): String = dataStoreRepository.readLanguage()

    fun themeSave(theme: String) {
        viewModelScope.launch {
            dataStoreRepository.saveTheme(theme)
        }
    }

    suspend fun themeRead(): String = dataStoreRepository.readTheme()

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