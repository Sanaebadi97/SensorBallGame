package info.sanaebadi.sensorballgame.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import info.sanaebadi.sensorballgame.BuildConfig
import timber.log.Timber

@HiltAndroidApp
class BallGameApp : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}