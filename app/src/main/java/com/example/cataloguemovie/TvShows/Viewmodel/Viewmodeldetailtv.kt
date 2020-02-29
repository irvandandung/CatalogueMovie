package com.example.cataloguemovie.TvShows.Viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cataloguemovie.Api.TvRepo
import com.example.cataloguemovie.TvShows.Data.TvShow

class Viewmodeldetailtv(tv_id: Int, lang: String) : ViewModel() {
    private val tvrepo: TvRepo = TvRepo()
    lateinit var tvshow: LiveData<TvShow>

    init {
        getDetailTv(tv_id, lang)
    }

    fun getDetailTv(tv_id: Int, lang: String) {
        tvshow = tvrepo.getDetailTvShow(tv_id, lang)
    }
}

class Viewmodeldetailtvfactory(private val tv_id: Int?, private val lang: String) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return tv_id?.let { Viewmodeldetailtv(it, lang) } as T
    }
}