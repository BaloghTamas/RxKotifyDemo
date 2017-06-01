package hu.bme.aut.android.kotifydemo.ui.artists

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hu.bme.aut.android.kotifydemo.R
import hu.bme.aut.android.kotifydemo.injector
import hu.bme.aut.android.kotifydemo.model.Item
import hu.bme.aut.android.kotifydemo.ui.utils.hide
import hu.bme.aut.android.kotifydemo.ui.utils.show
import hu.bme.aut.android.kotifydemo.ui.utils.showLongToast
import kotlinx.android.synthetic.main.fragment_artists.*
import javax.inject.Inject

class ArtistsFragment : Fragment(), ArtistsScreen {

    val artistsList: MutableList<Item> = mutableListOf()
    var artistsAdapter: ArtistsAdapter? = null
    var artist = "queen"

    @Inject
    lateinit var artistsPresenter: ArtistsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        artist = arguments.getString(KEY_ARTIST)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        injector.inject(this)
        artistsPresenter.attachScreen(this)
    }


    companion object {
        val KEY_ARTIST = "KEY_ARTIST"

        fun newInstance(artist: String): ArtistsFragment {
            val fragment = ArtistsFragment()
            val bundle = Bundle()

            bundle.putString(KEY_ARTIST, artist)
            fragment.arguments = bundle
            return fragment
        }
    }


    override fun onDetach() {
        artistsPresenter.detachScreen()
        super.onDetach()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_artists, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        etArtist.setText(artist)
        val llm = LinearLayoutManager(context)
        llm.orientation = LinearLayoutManager.VERTICAL
        recyclerViewArtists.layoutManager = llm

        artistsAdapter = ArtistsAdapter(context, artistsList)
        recyclerViewArtists.adapter = artistsAdapter

        swipeRefreshLayoutArtists.setOnRefreshListener {
            artist = etArtist.text.toString()
            artistsPresenter.refreshArtists(artist)
        }
    }

    override fun onResume() {
        super.onResume()
        artistsPresenter.refreshArtists(artist)
    }

    override fun showArtists(artists: MutableList<Item>?) {
        swipeRefreshLayoutArtists.isRefreshing = false
        artistsList.clear()
        if (artists != null) {
            artistsList.addAll(artists)
        }
        artistsAdapter?.notifyDataSetChanged()

        if (artistsList.isEmpty()) {
            recyclerViewArtists.hide()
            tvEmpty.show()
        } else {
            recyclerViewArtists.show()
            tvEmpty.hide()
        }

    }

    override fun showNetworkError(errorMsg: String) {
        swipeRefreshLayoutArtists.isRefreshing = false
        activity.showLongToast(errorMsg)
    }
}
