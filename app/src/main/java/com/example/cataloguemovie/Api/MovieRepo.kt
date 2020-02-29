package com.example.cataloguemovie.Api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cataloguemovie.Movies.Data.DataMovies
import com.example.cataloguemovie.Movies.Data.Movies
import retrofit2.Call
import retrofit2.Response

class MovieRepo {
    private var api = Retrofitbuilder().createservicemovie(ApiServices::class.java)
    private var apisearch = Retrofitbuilder().createservicesearch(ApiServices::class.java)
    private var apidiscover = Retrofitbuilder().createreleasemovie(ApiServices::class.java)
    fun getPopularMovies(page: Int, language: String): LiveData<DataMovies> {
        val livdata = MutableLiveData<DataMovies>()
        api.getPopularMovies(page = page, language = language).enqueue(object : retrofit2.Callback<DataMovies> {
            override fun onFailure(call: Call<DataMovies>, t: Throwable) {
                livdata.postValue(null)
            }

            override fun onResponse(call: Call<DataMovies>, response: Response<DataMovies>) {
                livdata.postValue(response.body())
            }

        })

        return livdata
    }

    fun getDetailMovie(movie_id: Int, language: String): LiveData<Movies> {
        val livdata = MutableLiveData<Movies>()
        api.getDetailMovie(movie_id = movie_id, language = language).enqueue(object : retrofit2.Callback<Movies> {
            override fun onFailure(call: Call<Movies>, t: Throwable) {
                livdata.postValue(null)
            }

            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                livdata.postValue(response.body())
            }
        })
        return livdata
    }

    fun getSearchMovie(query : String, language: String) : LiveData<DataMovies>{
        val livdata = MutableLiveData<DataMovies>()
        apisearch.getSearchMovie(query = query, language = language).enqueue(object : retrofit2.Callback<DataMovies>{
            override fun onFailure(call: Call<DataMovies>, t: Throwable) {
                livdata.postValue(null)
            }

            override fun onResponse(call: Call<DataMovies>, response: Response<DataMovies>) {
                livdata.postValue(response.body())
            }
        })

        return livdata
    }

    fun getMovieReleaseToday(language: String, releaseDate : String) : LiveData<DataMovies>{
        val livdata = MutableLiveData<DataMovies>()
        apidiscover.getMovieReleaseToday(language = language, releasegte = releaseDate, releaselte = releaseDate).enqueue(object : retrofit2.Callback<DataMovies>{
            override fun onFailure(call: Call<DataMovies>, t: Throwable) {
                livdata.postValue(null)
            }

            override fun onResponse(call: Call<DataMovies>, response: Response<DataMovies>) {
                livdata.postValue(response.body())
            }
        })
        return livdata
    }
}