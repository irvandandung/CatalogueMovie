package com.example.cataloguemovie.Movies


import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cataloguemovie.Movies.Adapter.ListMoviesAdapter
import com.example.cataloguemovie.Movies.Adapter.OnItemClickCallback
import com.example.cataloguemovie.Movies.Data.Movies
import com.example.cataloguemovie.Movies.Detail.DetailMovies
import com.example.cataloguemovie.Movies.Viewmodel.ViewModelMovieSearch
import com.example.cataloguemovie.Movies.Viewmodel.ViewModelMovieSearchFactory
import com.example.cataloguemovie.Movies.Viewmodel.Viewlistmovie
import com.example.cataloguemovie.Movies.Viewmodel.Viewlistmoviefactory
import com.example.cataloguemovie.R
import kotlinx.android.synthetic.main.fragment_movies.*
import kotlinx.android.synthetic.main.fragment_movies.view.*

class FragmentMovies : Fragment() {
    private var list = ArrayList<Movies>()
    lateinit var code_lang: String
    private lateinit var viewModel: Viewlistmovie
    private lateinit var viewModelSearch : ViewModelMovieSearch
    private lateinit var listMoviesAdapter: ListMoviesAdapter
    private var isResponse = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View = inflater.inflate(R.layout.fragment_movies, container, false)
        return view
    }

    private fun getAlldataOnFragment(codelang : String){
        viewModel = ViewModelProviders.of(this, Viewlistmoviefactory(1, codelang)).get(Viewlistmovie::class.java)
        viewModel.movie.observe(this, Observer { movieItem ->
            isResponse = true
            if (movieItem != null) {
                list = movieItem.movie as ArrayList<Movies>
                showLoading(false)
                showRecyclerList()
            } else {
                Toast.makeText(context, "Data tidak ada atau internet mati", Toast.LENGTH_LONG).show()
            }
        })
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_movies.setHasFixedSize(true)
        showLoading(true)
        code_lang = getString(R.string.codelang)
        view.Bt_search.setOnClickListener {
            showLoading(true)
            getDatawithquery(code_lang, view)
        }
        getAlldataOnFragment(code_lang)
    }

    private fun getDatawithquery(codelang: String, view: View){
        val query : String = view.Et_search.text.toString()
//        Log.d("query", query)
        viewModelSearch = ViewModelProviders.of(this, ViewModelMovieSearchFactory(query, codelang)).get(ViewModelMovieSearch::class.java)
        viewModelSearch.setDetailS(query, codelang)
        viewModelSearch.getDetailS().observe(this, Observer {
            movieItem ->
            isResponse = true
            if (movieItem != null) {
                list = movieItem.movie as ArrayList<Movies>
                showLoading(false)
                showRecyclerList()
            } else {
                Toast.makeText(context, "Data tidak ada atau internet mati", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun showRecyclerList() {
        rv_movies?.layoutManager = LinearLayoutManager(context)
        listMoviesAdapter = ListMoviesAdapter(list)
        listMoviesAdapter.notifyDataSetChanged()
        rv_movies?.adapter = listMoviesAdapter

        listMoviesAdapter.setOnItemClickCallback(object : OnItemClickCallback {
            override fun onItemClicked(movies: Movies) {
                showSelectedHero(movies)
                val intent = Intent(activity, DetailMovies::class.java)
                intent.putExtra(DetailMovies.EXTRA_MOVIES, movies)
                startActivity(intent)
            }
        })
    }

    private fun showSelectedHero(movies: Movies) {
        Toast.makeText(activity, "${R.string.open} ${movies.title}", Toast.LENGTH_SHORT).show()
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            movieView.visibility = View.VISIBLE
        } else {
            movieView.visibility = View.GONE
        }
    }
}

