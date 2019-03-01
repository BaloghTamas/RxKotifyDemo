package hu.bme.aut.android.kotifydemo

import android.content.Context
import dagger.Module
import dagger.Provides
import hu.bme.aut.android.kotifydemo.ui.UIModule
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
class AndroidTestModule(context: Context) {

    private val uiModule: UIModule = UIModule(context)

    @Provides
    fun provideContext() = uiModule.context()


    @Provides
    @Singleton
    fun provideNetworkScheduler() = Schedulers.from(Executors.newFixedThreadPool(1))
}
