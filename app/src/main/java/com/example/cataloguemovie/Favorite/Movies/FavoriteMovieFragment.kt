package com.example.cataloguemovie.Favorite.Movies


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
import com.example.cataloguemovie.Movies.Db.MovieEntity
import com.example.cataloguemovie.Movies.Db.RoomMovieConnect

import com.example.cataloguemovie.R
import kotlinx.android.synthetic.main.fragment_favorite_movie.*
import kotlinx.android.synthetic.main.fragment_favorite_tv_show.*
import kotlinx.android.synthetic.main.fragment_movies.*
import kotlinx.android.synthetic.main.fragment_movies.rv_movies


class FavoriteMovieFragment : Fragment() {

    private lateinit var adapterFavoriteMovies: AdapterFavoriteMovies
    private lateinit var movieEntity: LiveData<List<MovieEntity>>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite_movie, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.actionBar?.setDisplayShowHomeEnabled(false)
        val moviedao = RoomMovieConnect.getInstance(activity!!.applicationContext)
        val listmovie = moviedao?.movieDao()?.alldatamovie()
        listmovie?.observe(this, Observer { moviee ->
            showLoading(false)
            rv_moviess.layoutManager = LinearLayoutManager(context)
            adapterFavoriteMovies = AdapterFavoriteMovies(moviee)
            adapterFavoriteMovies.notifyDataSetChanged()
            rv_moviess.adapter = adapterFavoriteMovies

            adapterFavoriteMovies.setOnItemClickCallback(
                object : AdapterFavoriteMovies.OnItemClickCallback {
                    override fun onItemClicked(data: MovieEntity) {
                        val i = Intent(activity, DetailFavoriteMovie::class.java)
                        i.putExtra(DetailFavoriteMovie.EXTRA_MOVIE_FAV, data)
                        startActivity(i)
                        showSelectedHero(data)
                    }
                }
            )
        })
    }

    private fun showSelectedHero(data: MovieEntity) {
        Toast.makeText(activity, "${R.string.open} ${data.originalTitle}", Toast.LENGTH_LONG).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLoading(true)
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            movieVieww.visibility = View.VISIBLE
        } else {
            movieVieww.visibility = View.GONE
        }
    }
}
