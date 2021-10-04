package info.sanaebadi.sensorballgame.intro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import info.sanaebadi.sensorballgame.data.DataStorePreference
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class OnboardingViewModel @Inject constructor(private val preference: DataStorePreference) :
    ViewModel() {


    fun saveOnboarding(save: Boolean) {
        viewModelScope.launch {
            preference.saveOnboarding(save)
        }
    }

    fun fetchOnboarding() = preference.fetchOnboarding().asLiveData()
}