package com.example.cataloguemovie.TvShows.Viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cataloguemovie.Api.TvRepo
import com.example.cataloguemovie.TvShows.Data.DataTvShow

class ViewModelTvSearch(query:String, language:String):ViewModel(){
    val tvRepo = TvRepo()
    lateinit var searchtv : LiveData<DataTvShow>

//    init {
//        getDetailSerch(query, language)
//    }
//    fun getDetailSerch(query : String, language: String){
//        searchtv = tvRepo.getSearchTv(query = query, language = language)
//    }

    fun getSetDetailS(query : String, language: String){
        searchtv = tvRepo.getSearchTv(query = query, language = language)
    }

    fun getDetailS():LiveData<DataTvShow>{
        return searchtv
    }
}

class Viewmodeltvsearchfactory(private val query: String, private val language: String) : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return  ViewModelTvSearch(query, language) as T
    }
}