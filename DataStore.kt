package com.example.myapplication.datastore

import android.annotation.SuppressLint
import android.content.Context
import android.preference.Preference
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.DefineComponent
import dagger.hilt.EntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@SuppressLint("StaticFieldLeak")
class DataStore(private val context: Context) {

    companion object{
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
    }

    suspend fun getData(){
        val result: Flow<Int> = context.dataStore.data.map {
            p -> p[DataStoreKeys.item] ?: 0
        }
    }

    suspend fun setData(valuetoset: Int){
        context.dataStore.edit {
            it[DataStoreKeys.item] = valuetoset
        }
    }

    suspend fun customsetData(valuetoset: Int, key: Preferences.Key<Int>){
        context.dataStore.edit {
            it[key] = valuetoset
        }
    }

    suspend fun getCustomData(key: Preferences.Key<Int>): Flow<Int>{
        val result: Flow<Int> = context.dataStore.data.map {
                p -> p[key] ?: 0
        }

        return result
    }
}

object DataStoreKeys{
    val item = intPreferencesKey("val1")
    val theme: Preferences.Key<Int> = intPreferencesKey("theme")
    val item2 = stringPreferencesKey("val2")
}