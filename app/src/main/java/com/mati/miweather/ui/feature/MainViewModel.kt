package com.mati.miweather.ui.feature

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    private val _city: MutableState<MovieState> = mutableStateOf(MovieState())
    val city: State<MovieState> = _city

    val location = "تهران"
    val apiKey = "489c59265fb912e0096ba30d3fa6e79f"
    val language = "fa"
    init {
        apiService.getWeather(location, apiKey, language).enqueue(object : Callback<CitysStatus> {
            override fun onResponse(call: Call<CitysStatus>, response: Response<CitysStatus>) {
                viewModelScope.launch {
                    _city.value = MovieState(data = response.body())
                }
            }

            override fun onFailure(call: Call<CitysStatus>, t: Throwable) {
                _city.value = MovieState(error = t.message.toString())
            }

        })
    }

}

data class MovieState(
    val data: CitysStatus? = null,
    val error: String = " ",
    val isLoading: Boolean = false,
)