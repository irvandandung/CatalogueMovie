package com.example.cataloguemovie.Api

import com.example.cataloguemovie.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retrofitbuilder {
    fun <T> createservicetv(mClass: Class<T>): T {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL_TV)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(mClass)
    }

    fun <T> createservicemovie(mClass: Class<T>): T {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL_MOVIE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(mClass)
    }

    fun <T> createservicesearch(mClass : Class<T>): T {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.SEARCH_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(mClass)
    }

    fun <T> createreleasemovie(mClass : Class<T>):T {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.DISCOVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(mClass)
    }
}