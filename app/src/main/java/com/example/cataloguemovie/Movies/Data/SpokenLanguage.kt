package com.example.cataloguemovie.Movies.Data


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SpokenLanguage(
    @SerializedName("iso_639_1")
    var iso6391: String?,
    @SerializedName("name")
    var name: String?
) : Parcelable