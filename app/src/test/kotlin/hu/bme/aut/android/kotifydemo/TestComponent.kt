package hu.bme.aut.android.kotifydemo

import dagger.Component
import hu.bme.aut.android.kotifydemo.interactor.InteractorModule
import hu.bme.aut.android.kotifydemo.mock.MockNetworkModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(MockNetworkModule::class, TestModule::class, InteractorModule::class))
interface TestComponent : KotifyDemoApplicationComponent
