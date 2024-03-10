package com.example.notanotherweatherapp

import android.content.Context

class PreferenceManager(context: Context) {
    private val sharedPreferences =
        context.getSharedPreferences("NAWAPreferences", Context.MODE_PRIVATE)

    fun savePreference(preference: Preference, value: String) {
        val editor = sharedPreferences.edit()
        editor.putString(preference.key, value)
        editor.apply()
    }

    fun getPreference(preference: Preference): String =
        with(preference) {
            sharedPreferences.getString(key, defaultValue) ?: defaultValue
        }
}