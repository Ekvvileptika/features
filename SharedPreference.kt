package com.example.myapplication.sharedpreferece

import android.content.Context
import android.content.SharedPreferences


class SharedPreference(private val context: Context) {
    private val PERSISTANT_STORAGE_NAME = "STORAGENAME"
    var data: SharedPreferences = context.getSharedPreferences(PERSISTANT_STORAGE_NAME, Context.MODE_PRIVATE)

    fun getIntData(key: String): Int = data.getInt(key, 0)

    fun putIntData(key: String, value: Int){
        data.edit().putInt(key, value).apply()
    }
}

object SharedName{
    val theme = "THEME"
}