package com.example.cataloguemovie.Movies.Viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cataloguemovie.Api.MovieRepo
import com.example.cataloguemovie.Movies.Data.DataMovies

class Viewlistmovie(pages: Int, languages: String) : ViewModel() {
    private val movieRepo = MovieRepo()
    lateinit var movie: LiveData<DataMovies>

    init {
        getPopularcadangan(pages, languages)
    }

    fun getPopularcadangan(page: Int, language: String) {
        movie = movieRepo.getPopularMovies(page, language)
    }
}

class Viewlistmoviefactory(private val page: Int, private val language: String) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return Viewlistmovie(page, language) as T
    }
}