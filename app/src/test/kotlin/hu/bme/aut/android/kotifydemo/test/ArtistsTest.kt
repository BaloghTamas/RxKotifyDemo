package hu.bme.aut.android.kotifydemo.test

import hu.bme.aut.android.kotifydemo.BuildConfig
import hu.bme.aut.android.kotifydemo.model.Item
import hu.bme.aut.android.kotifydemo.setTestInjector
import hu.bme.aut.android.kotifydemo.ui.artists.ArtistsPresenter
import hu.bme.aut.android.kotifydemo.ui.artists.ArtistsScreen
import hu.bme.aut.android.kotifydemo.utils.argumentCaptor
import hu.bme.aut.android.kotifydemo.utils.mock
import junit.framework.Assert.assertTrue
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.verify
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class)
class ArtistsTest {

    private lateinit var artistsPresenter: ArtistsPresenter
    private lateinit var artistsScreen: ArtistsScreen
    private lateinit var query: String

    @Before
    @Throws(Exception::class)
    fun setup() {
        setTestInjector()
        artistsScreen = mock()
        artistsPresenter = ArtistsPresenter()
        artistsPresenter.attachScreen(artistsScreen)
    }

    @Test
    fun testArtists() {
        query = "AC/DC"
        artistsPresenter.refreshArtists(query)

        val list = argumentCaptor<MutableList<Item>>()
        verify(artistsScreen).showArtists(list.capture())
        assertTrue(list.value.size > 0)
    }


    @After
    fun tearDown() {
        artistsPresenter.detachScreen()
    }

}