package info.sanaebadi.sensorballgame.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStorePreference @Inject constructor(private val preferences: DataStore<Preferences>) {

    suspend fun saveOnboarding(save:Boolean) {
        preferences.edit {
            it[preferencesKey<Boolean>(ONBOARD_KEY)] = save
        }
    }

     fun fetchOnboarding() = preferences.data.map {
             it[preferencesKey<Boolean>(ONBOARD_KEY)] ?: false  }


    companion object{
       const val ONBOARD_KEY = "onBoard"
    }
}