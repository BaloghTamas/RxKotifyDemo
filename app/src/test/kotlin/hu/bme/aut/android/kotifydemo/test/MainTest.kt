package hu.bme.aut.android.kotifydemo.test

import hu.bme.aut.android.kotifydemo.BuildConfig
import hu.bme.aut.android.kotifydemo.setTestInjector
import hu.bme.aut.android.kotifydemo.ui.main.MainPresenter
import hu.bme.aut.android.kotifydemo.ui.main.MainScreen
import hu.bme.aut.android.kotifydemo.utils.mock
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.verify
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class)
class MainTest {

    private lateinit var mainPresenter: MainPresenter
    private lateinit var mainScreen: MainScreen


    @Throws(Exception::class)
    @Before fun setup() {
        setTestInjector()
        mainScreen = mock()
        mainPresenter = MainPresenter()
        mainPresenter.attachScreen(mainScreen)
    }

    @Test fun testSearch() {
        val artist = "AC/DC"
        mainPresenter.showArtistsSearchList(artist)
        verify(mainScreen).showArtists(artist)
    }


    @After fun tearDown() {
        mainPresenter.detachScreen()
    }

}