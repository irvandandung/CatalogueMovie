package com.example.cataloguemovie.Api

import com.example.cataloguemovie.BuildConfig
import com.example.cataloguemovie.Movies.Data.DataMovies
import com.example.cataloguemovie.Movies.Data.Movies
import com.example.cataloguemovie.TvShows.Data.DataTvShow
import com.example.cataloguemovie.TvShows.Data.TvShow
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {

    //Movies
    @GET("popular")
    fun getPopularMovies(
        @Query("api_key") apikey: String = BuildConfig.TSDB_API_KEY,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<DataMovies>

    @GET("{movie_id}")
    fun getDetailMovie(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") apikey: String = BuildConfig.TSDB_API_KEY,
        @Query("language") language: String
    ): Call<Movies>

    @GET("movie")
    fun getSearchMovie(
        @Query("api_key") apikey: String = BuildConfig.TSDB_API_KEY,
        @Query("languange") language: String,
        @Query("query") query : String
    ):Call<DataMovies>

    @GET("movie")
    fun getMovieReleaseToday(
        @Query("api_key")  apikey: String = BuildConfig.TSDB_API_KEY,
        @Query("language") language: String,
        @Query("release_date.gte") releasegte : String,
        @Query("release.lte") releaselte :String
    ):Call<DataMovies>

    //TV_SHOW
    @GET("popular")
    fun getPopularTV(
        @Query("api_key") apikey: String = BuildConfig.TSDB_API_KEY,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<DataTvShow>

    @GET("{tv_id}")
    fun getDetailTV(
        @Path("tv_id") tv_id: Int,
        @Query("api_key") apikey: String = BuildConfig.TSDB_API_KEY,
        @Query("language") language: String
    ): Call<TvShow>

    @GET("tv")
    fun getSearchTv(
        @Query("api_key") apikey: String = BuildConfig.TSDB_API_KEY,
        @Query("languange") language: String,
        @Query("query") query : String
    ):Call<DataTvShow>
}