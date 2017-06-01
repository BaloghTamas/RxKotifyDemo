package hu.bme.aut.android.kotifydemo


import org.robolectric.RuntimeEnvironment
import org.robolectric.shadows.ShadowLog

fun setTestInjector() {
    ShadowLog.stream = System.out
    val application = RuntimeEnvironment.application as KotifyDemoApplication
    val component = DaggerTestComponent.builder().testModule(TestModule(application.applicationContext)).build()
    InjectorDelegate.overrideInjector(component)
}

