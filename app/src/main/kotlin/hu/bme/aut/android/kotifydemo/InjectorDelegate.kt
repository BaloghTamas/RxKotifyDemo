package hu.bme.aut.android.kotifydemo

import android.content.Context
import hu.bme.aut.android.kotifydemo.ui.UIModule
import kotlin.reflect.KProperty

object InjectorDelegate {
    var context: Context? = null
    private var component: KotifyDemoApplicationComponent? = null

    operator fun getValue(thisRef: Any?, property: KProperty<*>): KotifyDemoApplicationComponent {
        if (component == null) {
            component = DaggerKotifyDemoApplicationComponent.builder().uIModule(UIModule(context!!)).build()
        }
        return component!!
    }

    fun overrideInjector(value: KotifyDemoApplicationComponent) {
        component = value
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: KotifyDemoApplicationComponent) {
    }

}