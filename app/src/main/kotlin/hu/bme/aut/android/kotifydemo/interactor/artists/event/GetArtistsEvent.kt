package hu.bme.aut.android.kotifydemo.interactor.artists.event

import hu.bme.aut.android.kotifydemo.model.Item

data class GetArtistsEvent(
        var code: Int = 0,
        var artists: MutableList<Item>? = null,
        var throwable: Throwable? = null)
