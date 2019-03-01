package hu.bme.aut.android.kotifydemo.ui.main

import hu.bme.aut.android.kotifydemo.ui.Screen

interface MainScreen : Screen {
    fun showArtists(artistSearchTerm: String)
}
