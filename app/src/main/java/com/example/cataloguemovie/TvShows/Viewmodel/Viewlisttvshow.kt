package com.example.cataloguemovie.TvShows.Viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cataloguemovie.Api.TvRepo
import com.example.cataloguemovie.TvShows.Data.DataTvShow
import com.example.cataloguemovie.TvShows.Data.TvShow

class Viewlisttvshow(pages: Int, langs: String) : ViewModel() {
    private val tvrepo: TvRepo? = TvRepo()
    lateinit var tvshow: LiveData<DataTvShow>

    init {
        getTvListrepo(pages, langs)
    }

    fun getTvListrepo(page: Int, lang: String) {
        if (tvrepo != null) {
            tvshow = tvrepo.getPopularTv(page, lang)
        }
    }
}

class Viewlisttvshowfactory(private val page: Int, private val lang: String) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return Viewlisttvshow(page, lang) as T
    }
}