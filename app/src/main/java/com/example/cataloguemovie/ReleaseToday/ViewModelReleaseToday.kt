package com.example.cataloguemovie.ReleaseToday

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cataloguemovie.Api.MovieRepo
import com.example.cataloguemovie.Movies.Data.DataMovies

class ViewModelReleaseToday(language: String, releaseDate:String) : ViewModel(){
    private val movieRepo = MovieRepo()
    lateinit var movie: LiveData<DataMovies>

    init {
        getMovieReleaseData(language, releaseDate)
    }

    fun getMovieReleaseData(language: String, releaseDate: String){
        movie = movieRepo.getMovieReleaseToday(language, releaseDate)
    }
}

class ViewModelReleaseTodayFactory(private val language: String, private val releaseDate: String):ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ViewModelReleaseToday(language, releaseDate) as T
    }
}