package com.example.cataloguemovie.ReleaseToday

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cataloguemovie.MainActivity
import com.example.cataloguemovie.Movies.Adapter.ListMoviesAdapter
import com.example.cataloguemovie.Movies.Adapter.OnItemClickCallback
import com.example.cataloguemovie.Movies.Data.Movies
import com.example.cataloguemovie.Movies.Detail.DetailMovies
import com.example.cataloguemovie.R
import kotlinx.android.synthetic.main.activity_main2.movieView
import kotlinx.android.synthetic.main.activity_main2.rv_movies

class MainReleaseActivity : AppCompatActivity() {
    companion object{
        const val DATE_REALESE = "realese"
    }
    private var list = ArrayList<Movies>()
    lateinit var code_lang: String
    private lateinit var listMoviesAdapter: ListMoviesAdapter
    private lateinit var viewModelReleaseToday: ViewModelReleaseToday
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val getDatefromnotif = intent.getStringExtra(DATE_REALESE)
        supportActionBar?.title = getString(R.string.titlerilis) + getDatefromnotif + "*"
        setContentView(R.layout.activity_main2)
        code_lang = getString(R.string.codelang)
        showLoading(true)
        viewModelReleaseToday = ViewModelProviders.of(this, ViewModelReleaseTodayFactory(code_lang, getDatefromnotif)).get(ViewModelReleaseToday::class.java)
        viewModelReleaseToday.movie.observe(this, Observer {
          moviee ->
            list = moviee.movie as ArrayList<Movies>
            showLoading(false)
            showRecyclerList()
        })
    }

    private fun showRecyclerList() {
        rv_movies?.layoutManager = LinearLayoutManager(this)
        listMoviesAdapter = ListMoviesAdapter(list)
        listMoviesAdapter.notifyDataSetChanged()
        rv_movies?.adapter = listMoviesAdapter

        listMoviesAdapter.setOnItemClickCallback(object : OnItemClickCallback {
            override fun onItemClicked(movies: Movies) {
                showSelectedHero(movies)
                val intent = Intent(this@MainReleaseActivity, DetailMovies::class.java)
                intent.putExtra(DetailMovies.EXTRA_MOVIES, movies)
                startActivity(intent)
            }
        })
    }

    private fun showSelectedHero(movies: Movies) {
        Toast.makeText(this, "${R.string.open} ${movies.title}", Toast.LENGTH_SHORT).show()
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            movieView.visibility = View.VISIBLE
        } else {
            movieView.visibility = View.GONE
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
        super.onBackPressed()
    }

}
