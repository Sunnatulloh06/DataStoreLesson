package com.example.datastorelesson

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.datastorelesson.ui.theme.Purple80
import com.example.datastorelesson.ui.theme.Red
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("user_data")

class DataStoreManager(val context: Context) {

    suspend fun saveSettings(settingsData: SettingsData) {
        withContext(Dispatchers.IO){
            context.dataStore.edit { pref ->
                pref[intPreferencesKey("text_size")] = settingsData.textSize
                pref[longPreferencesKey("background_color")] = settingsData.bgColor
            }
        }
    }

    fun getSettings() = context.dataStore.data.map { pref ->
        return@map SettingsData (
            pref[intPreferencesKey("text_size")] ?: 40,
            pref[longPreferencesKey("background_color")] ?: Red.value.toLong()
        )
    }
}