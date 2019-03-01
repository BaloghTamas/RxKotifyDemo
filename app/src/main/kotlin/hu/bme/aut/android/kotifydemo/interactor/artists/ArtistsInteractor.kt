package hu.bme.aut.android.kotifydemo.interactor.artists

import hu.bme.aut.android.kotifydemo.model.ArtistsResult
import hu.bme.aut.android.kotifydemo.model.Item
import hu.bme.aut.android.kotifydemo.network.ArtistsApi
import hu.bme.aut.android.kotifydemo.network.TokenApi
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class ArtistsInteractor @Inject constructor(private var artistsApi: ArtistsApi, private var tokenApi: TokenApi) {

    fun getArtists(artistQuery: String): List<Item> {

        val tokenCall = tokenApi.getToken("client_credentials", "Basic MDM0MjY2ODU0OTI1NGZkOWFiMzdmMzNlZjRkNjRkYjA6ODM1MWFiMjVjYzVmNDBhMjg5OGI5N2U5ZjQyMmNkMDk=")

        try {
            val token = tokenCall.execute().body()
            val artistsQueryCall = artistsApi.getArtists("Bearer " + token!!.token, artistQuery, "artist", 0, 3)
            val response: Response<ArtistsResult>
            response = artistsQueryCall.execute()
            if (response.code() != 200) {
                throw RuntimeException("Result code is not 200")
            }
            return response.body()!!.artists!!.items!!
        } catch (e: IOException) {
            e.printStackTrace()
            throw RuntimeException(e)
        }

    }
}
