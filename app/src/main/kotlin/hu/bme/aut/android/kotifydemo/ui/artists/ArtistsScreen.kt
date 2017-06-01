package hu.bme.aut.android.kotifydemo.ui.artists

import hu.bme.aut.android.kotifydemo.model.Item

interface ArtistsScreen {
    fun showArtists(artists: MutableList<Item>?)
    fun showNetworkError(errorMsg: String)
}
