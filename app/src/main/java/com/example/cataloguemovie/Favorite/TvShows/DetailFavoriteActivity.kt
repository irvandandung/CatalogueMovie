package com.example.cataloguemovie.Favorite.TvShows

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.cataloguemovie.BuildConfig
import com.example.cataloguemovie.R
import com.example.cataloguemovie.TvShows.Db.RoomTvShowConnect
import com.example.cataloguemovie.TvShows.Db.TvShowEntity
import kotlinx.android.synthetic.main.activity_detail_favorite.*
import kotlinx.android.synthetic.main.activity_detail_favorite.tv_item_budget
import kotlinx.android.synthetic.main.activity_detail_favorite.tv_item_genre
import kotlinx.android.synthetic.main.activity_detail_favorite.tv_item_language
import kotlinx.android.synthetic.main.activity_detail_favorite.tv_item_revenue
import kotlinx.android.synthetic.main.activity_detail_favorite_movie.*
import kotlinx.android.synthetic.main.fragment_favorite_tv_show.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailFavoriteActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_TV_SHOW_FAV = "extra_tvshow"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_favorite)

        val tvshowdao = RoomTvShowConnect.getInstance(context = this)
        var tvshow = intent.getParcelableExtra<TvShowEntity>(EXTRA_TV_SHOW_FAV)
        showLoading(false)
        tv_item_budget.text = tvshow.popularity.toString()
        tv_overview.text = tvshow.overview
        tv_item_dateReleased.text = tvshow.firstAirDate
        tv_item_genre.text = tvshow.genre
        tv_item_language.text = tvshow.languages
        tv_item_revenue.text = tvshow.homepage
        tv_item_status.text = tvshow.status
        tv_item_runtime.text = tvshow.episodeRuntime
        title_movie.text = tvshow.name
        tv_userScore.text = tvshow.voteAverage.toString()
        Glide.with(this)
            .load(BuildConfig.URL_POSTER + tvshow.backdropPath)
            .apply(RequestOptions().override(350, 550))
            .into(posterr)

        favoritestatetv.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {
                tvshowdao.tvshowDao().delete(tvshowentity = tvshow)
            }
            Toast.makeText(this, "success delete data", Toast.LENGTH_LONG).show()
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            tvDetilf.visibility = View.VISIBLE
        } else {
            tvDetilf.visibility = View.GONE
        }
    }
}
