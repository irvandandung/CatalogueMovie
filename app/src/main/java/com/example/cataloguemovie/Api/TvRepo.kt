package com.example.cataloguemovie.Api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cataloguemovie.TvShows.Data.DataTvShow
import com.example.cataloguemovie.TvShows.Data.TvShow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvRepo {
    private var api = Retrofitbuilder().createservicetv(ApiServices::class.java)
    private var apisearch = Retrofitbuilder().createservicesearch(ApiServices::class.java)
    fun getPopularTv(page: Int, language: String): LiveData<DataTvShow> {
        val livdatatv = MutableLiveData<DataTvShow>()

        api.getPopularTV(page = page, language = language).enqueue(object : Callback<DataTvShow> {
            override fun onFailure(call: Call<DataTvShow>, t: Throwable) {
                livdatatv.postValue(null)
            }

            override fun onResponse(call: Call<DataTvShow>, response: Response<DataTvShow>) {
                livdatatv.postValue(response.body())
            }

        })
        return livdatatv
    }

    fun getDetailTvShow(tv_id: Int, language: String): LiveData<TvShow> {
        val datatv = MutableLiveData<TvShow>()

        api.getDetailTV(tv_id = tv_id, language = language).enqueue(object : Callback<TvShow> {
            override fun onFailure(call: Call<TvShow>, t: Throwable) {
                datatv.postValue(null)
            }

            override fun onResponse(call: Call<TvShow>, response: Response<TvShow>) {
                datatv.postValue(response.body())
            }

        })
        return datatv
    }

    fun getSearchTv(query : String, language: String): LiveData<DataTvShow>{
        val datatv = MutableLiveData<DataTvShow>()
        apisearch.getSearchTv(query = query, language = language).enqueue(object : Callback<DataTvShow>{
            override fun onFailure(call: Call<DataTvShow>, t: Throwable) {
                datatv.postValue(null)
            }

            override fun onResponse(call: Call<DataTvShow>, response: Response<DataTvShow>) {
                datatv.postValue(response.body())
            }

        })
        return datatv
    }
}