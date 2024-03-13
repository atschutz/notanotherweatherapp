package com.example.notanotherweatherapp

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PreferenceManager @Inject constructor(@ApplicationContext context: Context) {
    private val sharedPreferences =
        context.getSharedPreferences("NAWAPreferences", Context.MODE_PRIVATE)

    fun savePreference(preference: Preference, value: String) {
        val editor = sharedPreferences.edit()
        editor.putString(preference.key, value)
        editor.apply()
    }

    fun savePreference(key: String, value: String) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getPreference(preference: Preference): String =
        with(preference) {
            sharedPreferences.getString(key, defaultValue) ?: defaultValue
        }

    fun getAllPreferences() = sharedPreferences.all
}