package com.example.cataloguemovie.Movies.Detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.cataloguemovie.BuildConfig
import com.example.cataloguemovie.Movies.Data.Movies
import com.example.cataloguemovie.Movies.Db.MovieEntity
import com.example.cataloguemovie.Movies.Db.RoomMovieConnect
import com.example.cataloguemovie.Movies.Viewmodel.Viewmodeldetailmovies
import com.example.cataloguemovie.Movies.Viewmodel.Viewmodeldetailmoviesfactory
import com.example.cataloguemovie.R
import kotlinx.android.synthetic.main.activity_detail_movies.*
import kotlinx.android.synthetic.main.activity_detail_movies.tv_item_budget
import kotlinx.android.synthetic.main.activity_detail_movies.tv_item_genre
import kotlinx.android.synthetic.main.activity_detail_movies.tv_item_language
import kotlinx.android.synthetic.main.activity_detail_movies.tv_item_revenue
import kotlinx.android.synthetic.main.activity_detail_movies.tv_item_userScore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailMovies : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIES = "extra_movies"
    }

    private lateinit var viewModel: Viewmodeldetailmovies
    private lateinit var code_lang: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movies)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val movies = intent?.getParcelableExtra(EXTRA_MOVIES) as Movies
        var id: Int? = movies.id
        val moviedao = RoomMovieConnect.getInstance(context = this)
        code_lang = getString(R.string.codelang)
        viewModel = ViewModelProviders.of(this, Viewmodeldetailmoviesfactory(id, code_lang))
            .get(Viewmodeldetailmovies::class.java)
        showLoading(true)
        viewModel.detailmovies.observe(this, Observer { moviee ->
            if (moviee != null) {
                showLoading(false)
                tv_item_userScore.text = moviee.voteAverage.toString()
                title_moviedetail.text = moviee.originalTitle
                tv_item_language.text = moviee.originalLanguage
                tv_item_revenue.text = moviee.revenue.toString()
                tv_item_genre.text = moviee.genres?.get(0)?.name.toString()
                tv_item_budget.text = moviee.budget.toString()
                tv_item_overview.text = moviee.overview
                tv_item_dateReleasedmovie.text = moviee.releaseDate
                tv_item_statusdetail.text = moviee.status
                tv_item_runtimedetail.text = moviee.runtime.toString()
                Glide.with(this)
                    .load(BuildConfig.URL_POSTER + moviee.backdropPath)
                    .apply(RequestOptions().override(350, 550))
                    .into(poster)

                favoritestate.setOnClickListener {
                    GlobalScope.launch(Dispatchers.IO) {
                        moviedao?.movieDao()?.insert(
                            MovieEntity(
                                id = moviee.id,
                                backdropPath = moviee.backdropPath,
                                budget = moviee.budget,
                                genres = moviee.genres?.get(0)?.name.toString(),
                                homepage = moviee.homepage,
                                originalLanguage = moviee.originalLanguage,
                                originalTitle = moviee.title,
                                overview = moviee.overview,
                                popularity = moviee.popularity,
                                releaseDate = moviee.releaseDate,
                                revenue = moviee.revenue,
                                runtime = moviee.runtime,
                                spokenLanguages = moviee.spokenLanguages?.get(0)?.name.toString(),
                                status = moviee.status,
                                voteAverage = moviee.voteAverage
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
            movieDetil.visibility = View.VISIBLE
        } else {
            movieDetil.visibility = View.GONE
        }
    }
}
