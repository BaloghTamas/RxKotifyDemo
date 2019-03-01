package hu.bme.aut.android.kotifydemo.ui

import android.content.Context
import dagger.Module
import dagger.Provides
import hu.bme.aut.android.kotifydemo.interactor.artists.ArtistsInteractor
import hu.bme.aut.android.kotifydemo.ui.artists.ArtistsPresenter
import hu.bme.aut.android.kotifydemo.ui.main.MainPresenter
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
class UIModule(private val context: Context) {

    @Provides
    fun context() = context

    @Provides
    @Singleton
    fun provideNetworkScheduler(): Scheduler = Schedulers.from(Executors.newFixedThreadPool(1))
}
