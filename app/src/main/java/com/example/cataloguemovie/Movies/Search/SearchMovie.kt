package com.example.cataloguemovie.Movies.Search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuItemCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cataloguemovie.Movies.Adapter.ListMoviesAdapter
import com.example.cataloguemovie.Movies.Adapter.OnItemClickCallback
import com.example.cataloguemovie.Movies.Data.Movies
import com.example.cataloguemovie.Movies.Detail.DetailMovies
import com.example.cataloguemovie.Movies.Viewmodel.ViewModelMovieSearch
import com.example.cataloguemovie.Movies.Viewmodel.ViewModelMovieSearchFactory
import com.example.cataloguemovie.R
import kotlinx.android.synthetic.main.activity_search_movie.*

class SearchMovie : AppCompatActivity() {
    companion object{
        const val SEARCH_MOVIE = "searchmovie"
    }
    private var list = ArrayList<Movies>()
    private lateinit var viewModelSearch : ViewModelMovieSearch
    private lateinit var listMoviesAdapter: ListMoviesAdapter
    lateinit var code_lang: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_movie)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        showLoading(false)
        code_lang = getString(R.string.codelang)
        val query = intent.getStringExtra(SEARCH_MOVIE)
        supportActionBar?.title = query
        getDatawithquery(code_lang,query)
    }

    private fun getDatawithquery(codelang: String, query:String){
//        Log.d("query", query)
        showLoading(true)
        viewModelSearch = ViewModelProviders.of(this, ViewModelMovieSearchFactory(query, codelang)).get(ViewModelMovieSearch::class.java)
        viewModelSearch.searchmovie.observe(this, Observer {
                movieItem ->
            if (movieItem != null) {
                list = movieItem.movie as ArrayList<Movies>
                showLoading(false)
                showRecyclerList()
            } else {
                Toast.makeText(this, "Data tidak ada atau internet mati", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun showRecyclerList() {
        rv_moviessearch.layoutManager = LinearLayoutManager(this)
        listMoviesAdapter = ListMoviesAdapter(list)
        listMoviesAdapter.notifyDataSetChanged()
        rv_moviessearch.adapter = listMoviesAdapter

        listMoviesAdapter.setOnItemClickCallback(object : OnItemClickCallback {
            override fun onItemClicked(movies: Movies) {
                showSelectedHero(movies)
                val intent = Intent(this@SearchMovie, DetailMovies::class.java)
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
            movieViewSearch.visibility = View.VISIBLE
        } else {
            movieViewSearch.visibility = View.GONE
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
