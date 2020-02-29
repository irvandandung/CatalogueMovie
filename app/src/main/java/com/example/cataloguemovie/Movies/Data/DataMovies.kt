package com.example.cataloguemovie.Movies.Data

import com.google.gson.annotations.SerializedName

data class DataMovies(
    @SerializedName("page") val page: Int?,
    @SerializedName("results") val movie: List<Movies>?,
    @SerializedName("total_pages") val pages: Int?
)