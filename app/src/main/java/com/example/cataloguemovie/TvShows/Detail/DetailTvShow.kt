package com.example.cataloguemovie.TvShows.Detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.cataloguemovie.BuildConfig
import com.example.cataloguemovie.R
import com.example.cataloguemovie.TvShows.Data.TvShow
import com.example.cataloguemovie.TvShows.Db.RoomTvShowConnect
import com.example.cataloguemovie.TvShows.Db.TvShowEntity
import com.example.cataloguemovie.TvShows.Viewmodel.Viewmodeldetailtv
import com.example.cataloguemovie.TvShows.Viewmodel.Viewmodeldetailtvfactory
import kotlinx.android.synthetic.main.activity_detail_tv_show.*
import kotlinx.android.synthetic.main.activity_detail_tv_show.title_movie
import kotlinx.android.synthetic.main.activity_detail_tv_show.tv_item_budget
import kotlinx.android.synthetic.main.activity_detail_tv_show.tv_item_dateReleased
import kotlinx.android.synthetic.main.activity_detail_tv_show.tv_item_genre
import kotlinx.android.synthetic.main.activity_detail_tv_show.tv_item_language
import kotlinx.android.synthetic.main.activity_detail_tv_show.tv_item_runtime
import kotlinx.android.synthetic.main.activity_detail_tv_show.tv_item_status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailTvShow : AppCompatActivity() {

    companion object {
        const val EXTRA_TV_SHOW = "extra_tv_show"
    }

    private var isFavorite = false
    private lateinit var code_lang: String
    private lateinit var vieModel: Viewmodeldetailtv
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tv_show)

        val tvshowdao = RoomTvShowConnect.getInstance(context = this)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        code_lang = getString(R.string.codelang)
        var tv_show = intent.getParcelableExtra<TvShow>(EXTRA_TV_SHOW)
        var id: Int?
        id = tv_show.id
        vieModel =
            ViewModelProviders.of(this, Viewmodeldetailtvfactory(id, code_lang)).get(Viewmodeldetailtv::class.java)
        showLoading(true)
        vieModel.tvshow.observe(this, Observer { tv ->
            if (tv != null) {
                showLoading(false)
                tv_overview.text = tv.overview
                tv_userScore.text = tv.voteAverage.toString()
                tv_item_dateReleased.text = tv.firstAirDate.toString()
                tv_item_status.text = tv.status
                title_movie.text = tv.name
                tv_item_budget.text = tv.popularity.toString()
                tv_item_language.text = tv.languages?.get(0).toString()
                tv_item_runtime.text = tv.episodeRunTime?.get(0).toString()
                tv_item_genre.text = tv.genres?.get(0)?.name.toString()
                Glide.with(this)
                    .load(BuildConfig.URL_POSTER + tv.backdropPath)
                    .apply(RequestOptions().override(350, 550))
                    .into(posterr)

                favoritestatetv.setOnClickListener {
                    GlobalScope.launch(Dispatchers.IO) {
                        isFavorite = true
                        tvshowdao.tvshowDao().insert(
                            TvShowEntity(
                                id = tv.id,
                                backdropPath = tv.backdropPath,
                                homepage = tv.homepage,
                                originalLanguage = tv.originalLanguage,
                                originalTitle = tv.originalName,
                                overview = tv.overview,
                                popularity = tv.popularity,
                                firstAirDate = tv.firstAirDate,
                                languages = tv.originalLanguage,
                                status = tv.status,
                                voteAverage = tv.voteAverage,
                                genre = tv.genres?.get(0)?.name.toString(),
                                episodeRuntime = tv.episodeRunTime?.get(0).toString(),
                                name = tv.name,
                                isFavorite = isFavorite
                            )
                        )
                    }
                    Toast.makeText(this, "input data success", Toast.LENGTH_LONG).show()

                }
            } else {
                Toast.makeText(this, "Data Kosong atau internet mati", Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun showLoading(state: Boolean) {
        if (state) {
            tvDetil.visibility = View.VISIBLE
        } else {
            tvDetil.visibility = View.GONE
        }
    }
}
