package hu.bme.aut.android.kotifydemo

import android.content.Context
import dagger.Module
import dagger.Provides
import hu.bme.aut.android.kotifydemo.di.Network
import hu.bme.aut.android.kotifydemo.ui.UIModule
import hu.bme.aut.android.kotifydemo.ui.artists.ArtistsPresenter
import hu.bme.aut.android.kotifydemo.ui.main.MainPresenter
import java.util.concurrent.Executor
import javax.inject.Singleton

@Module
class AndroidTestModule {

    private val UIModule: UIModule

    constructor(context: Context) {
        this.UIModule = UIModule(context)
    }

    @Provides
    fun provideContext(): Context {
        return UIModule.context()
    }

    @Provides
    @Singleton
    fun provideMainPresenter(): MainPresenter {
        return UIModule.mainPresenter()
    }

    @Provides
    @Singleton
    fun provideArtistsPresenter(): ArtistsPresenter {
        return UIModule.artistsPresenter()
    }

    @Provides
    @Singleton
    @Network
    fun provideNetworkExecutor(): Executor {
        return UIModule.networkExecutor()
    }

}
