package com.example.cataloguemovie.Movies.Viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cataloguemovie.Api.MovieRepo
import com.example.cataloguemovie.Movies.Data.DataMovies

class ViewModelMovieSearch(query : String, language:String) : ViewModel(){
    val movieRepo = MovieRepo()
    lateinit var searchmovie : LiveData<DataMovies>

    init {
        getDetailSerch(query, language)
    }

    fun getDetailSerch(query : String, language: String){
            searchmovie = movieRepo.getSearchMovie(query = query, language = language)
    }


//    fun getDetailS() : LiveData<DataMovies>{
//        return searchmovie
//    }
//
//    fun setDetailS(query : String, language: String){
//        searchmovie = movieRepo.getSearchMovie(query = query, language = language)
//    }
}

class ViewModelMovieSearchFactory(private val query: String, private val language: String) : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ViewModelMovieSearch(query, language) as T
    }
}