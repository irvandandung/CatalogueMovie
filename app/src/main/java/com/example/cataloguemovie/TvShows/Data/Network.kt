package com.example.cataloguemovie.TvShows.Data


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Network(
    @SerializedName("id")
    var id: Int?,
    @SerializedName("logo_path")
    var logoPath: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("origin_country")
    var originCountry: String?
) : Parcelable