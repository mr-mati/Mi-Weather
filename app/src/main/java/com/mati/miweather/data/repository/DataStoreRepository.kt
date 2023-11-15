package com.mati.miweather.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.mati.miweather.util.PREFERENCE_CITY_KEY
import com.mati.miweather.util.PREFERENCE_LANGUAGE_KEY
import com.mati.miweather.util.PREFERENCE_NAME
import com.mati.miweather.util.PREFERENCE_THEME_KEY
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCE_NAME)

@ViewModelScoped
class DataStoreRepository @Inject constructor(
    @ApplicationContext private val context: Context,
) {

    private object Keys {
        val cityKeys = stringPreferencesKey(PREFERENCE_CITY_KEY)
        val languageKey = stringPreferencesKey(PREFERENCE_LANGUAGE_KEY)
        val themeKey = stringPreferencesKey(PREFERENCE_THEME_KEY)
    }

    private val dataStore = context.dataStore

    suspend fun saveCityName(city: String) {
        dataStore.edit { preferences ->
            preferences[Keys.cityKeys] = city
        }
    }

    suspend fun readCityName(): String {
        return dataStore.data.first()[Keys.cityKeys] ?: "Tehran"
    }

    suspend fun saveLanguage(language: String) {
        dataStore.edit { preferences ->
            preferences[Keys.languageKey] = language
        }
    }

    suspend fun readLanguage(): String {
        return dataStore.data.first()[Keys.languageKey] ?: "en"
    }

    suspend fun saveTheme(language: String) {
        dataStore.edit { preferences ->
            preferences[Keys.themeKey] = language
        }
    }

    suspend fun readTheme(): String {
        return dataStore.data.first()[Keys.themeKey] ?: "light"
    }

}