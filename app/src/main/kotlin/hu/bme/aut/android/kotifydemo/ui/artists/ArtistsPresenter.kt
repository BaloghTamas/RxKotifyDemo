package hu.bme.aut.android.kotifydemo.ui.artists

import hu.bme.aut.android.kotifydemo.interactor.artists.ArtistsInteractor
import hu.bme.aut.android.kotifydemo.model.Item
import hu.bme.aut.android.kotifydemo.ui.RxPresenter
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class ArtistsPresenter @Inject constructor(
        private val artistsInteractor: ArtistsInteractor,
        private val scheduler: Scheduler
) : RxPresenter<ArtistsScreen>() {


    fun refreshArtists(artistQuery: String) {
        subscribe(single { artistsInteractor.getArtists(artistQuery) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(scheduler)
                .subscribe(
                        { artists: List<Item> -> screen?.showArtists(artists) },
                        { error: Throwable -> screen?.showNetworkError(error?.message ?: "Error") }
                )
        )
    }

}
