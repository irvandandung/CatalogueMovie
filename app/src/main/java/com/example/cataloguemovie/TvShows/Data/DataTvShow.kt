package com.example.cataloguemovie.TvShows.Data

import com.google.gson.annotations.SerializedName

data class DataTvShow(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val tv: List<TvShow>,
    @SerializedName("total_pages") val pages: Int
)