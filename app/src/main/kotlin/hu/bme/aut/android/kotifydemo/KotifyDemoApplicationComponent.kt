package hu.bme.aut.android.kotifydemo

import dagger.Component
import hu.bme.aut.android.kotifydemo.interactor.InteractorModule
import hu.bme.aut.android.kotifydemo.interactor.artists.ArtistsInteractor
import hu.bme.aut.android.kotifydemo.network.NetworkModule
import hu.bme.aut.android.kotifydemo.ui.UIModule
import hu.bme.aut.android.kotifydemo.ui.artists.ArtistsFragment
import hu.bme.aut.android.kotifydemo.ui.artists.ArtistsPresenter
import hu.bme.aut.android.kotifydemo.ui.main.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(UIModule::class, NetworkModule::class, InteractorModule::class))
interface KotifyDemoApplicationComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(artistsFragment: ArtistsFragment)
    fun inject(artistsInteractor: ArtistsInteractor)
    fun inject(artistsPresenter: ArtistsPresenter)
}
