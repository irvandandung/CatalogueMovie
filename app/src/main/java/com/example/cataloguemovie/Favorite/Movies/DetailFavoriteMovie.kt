package com.example.cataloguemovie.Favorite.Movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.cataloguemovie.BuildConfig
import com.example.cataloguemovie.Movies.Db.MovieEntity
import com.example.cataloguemovie.Movies.Db.RoomMovieConnect
import com.example.cataloguemovie.R
import kotlinx.android.synthetic.main.activity_detail_favorite_movie.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailFavoriteMovie : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE_FAV = "extra_movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_favorite_movie)
        val tvmovie = RoomMovieConnect.getInstance(context = this)
        var moviedata = intent.getParcelableExtra<MovieEntity>(EXTRA_MOVIE_FAV)
        showLoading(false)
        tv_item_userScore.text = moviedata.voteAverage.toString()
        title_moviedetail.text = moviedata.originalTitle
        tv_item_language.text = moviedata.originalLanguage
        tv_item_revenue.text = moviedata.revenue.toString()
        tv_item_genre.text = moviedata.genres
        tv_item_budget.text = moviedata.budget.toString()
        tv_item_overview.text = moviedata.overview
        tv_item_dateReleasedmovie.text = moviedata.releaseDate
        tv_item_statusdetail.text = moviedata.status
        tv_item_runtimedetail.text = moviedata.runtime.toString()
        Glide.with(this)
            .load(BuildConfig.URL_POSTER + moviedata.backdropPath)
            .apply(RequestOptions().override(350, 550))
            .into(poster)

        favoritestate.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {
                tvmovie?.movieDao()?.delete(moviedata)
            }
            Toast.makeText(this, "success delete data", Toast.LENGTH_LONG).show()
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            movieDetil.visibility = View.VISIBLE
        } else {
            movieDetil.visibility = View.GONE
        }
    }
}
