package hu.bme.aut.android.kotifydemo

import android.app.Application

class KotifyDemoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        InjectorDelegate.context = this
    }
}
