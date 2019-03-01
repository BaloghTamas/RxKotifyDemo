package hu.bme.aut.android.kotifydemo.ui.main

import hu.bme.aut.android.kotifydemo.ui.Presenter
import javax.inject.Inject

class MainPresenter @Inject constructor() : Presenter<MainScreen>() {

    fun showArtistsSearchList(artistSearchTerm: String) {
        screen?.showArtists(artistSearchTerm)
    }
}
