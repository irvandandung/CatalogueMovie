package com.example.cataloguemovie.Movies.Viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cataloguemovie.Api.MovieRepo
import com.example.cataloguemovie.Movies.Data.Movies

class Viewmodeldetailmovies(movie_id: Int, language: String) : ViewModel() {
    val movieRepo = MovieRepo()
    lateinit var detailmovies: LiveData<Movies>

    init {
        getDetailMovie(movie_id, language)
    }

    fun getDetailMovie(movie_id: Int, language: String) {
        detailmovies = movieRepo.getDetailMovie(movie_id, language)
    }
}


class Viewmodeldetailmoviesfactory(private val movie_id: Int?, private val language: String) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return movie_id?.let { Viewmodeldetailmovies(it, language) } as T
    }
}