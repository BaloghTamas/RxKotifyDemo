package hu.bme.aut.android.kotifydemo.ui.artists

import hu.bme.aut.android.kotifydemo.model.Item
import hu.bme.aut.android.kotifydemo.ui.Screen

interface ArtistsScreen : Screen {
    fun showArtists(artists: List<Item>?)
    fun showNetworkError(errorMsg: String)
}
