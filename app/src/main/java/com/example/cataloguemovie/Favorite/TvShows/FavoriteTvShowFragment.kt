package com.example.cataloguemovie.Favorite.TvShows

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.cataloguemovie.R
import com.example.cataloguemovie.TvShows.Db.RoomTvShowConnect
import com.example.cataloguemovie.TvShows.Db.TvShowEntity
import kotlinx.android.synthetic.main.fragment_favorite_tv_show.*
import kotlinx.android.synthetic.main.fragment_tv_show.rv_tv_show

class FavoriteTvShowFragment : Fragment() {
    private lateinit var tvshow: LiveData<List<TvShowEntity>>
    private lateinit var adapterFavoriteTvShows: AdapterFavoriteTvShows
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite_tv_show, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tvshowdao = RoomTvShowConnect.getInstance(activity!!.applicationContext)
        tvshow = tvshowdao.tvshowDao().alldatatvshow()
        tvshow.observe(this, Observer { tv ->
            showLoading(false)
            rv_tv_show.layoutManager = LinearLayoutManager(context)
            adapterFavoriteTvShows = AdapterFavoriteTvShows(tv)
            adapterFavoriteTvShows.notifyDataSetChanged()
            rv_tv_show.adapter = adapterFavoriteTvShows
            adapterFavoriteTvShows.setOnItemClickCallback(
                object : AdapterFavoriteTvShows.OnItemClickCallback {
                    override fun onItemClicked(data: TvShowEntity) {
                        val i = Intent(activity, DetailFavoriteActivity::class.java)
                        i.putExtra(DetailFavoriteActivity.EXTRA_TV_SHOW_FAV, data)
                        startActivity(i)
                        showSelectedHero(data)
                    }
                }
            )
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLoading(true)
    }

    private fun showSelectedHero(tvShow: TvShowEntity) {
        Toast.makeText(activity, "${R.string.open} ${tvShow.name}", Toast.LENGTH_LONG).show()
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            tvprogress.visibility = View.VISIBLE
        } else {
            tvprogress.visibility = View.GONE
        }
    }
}
