package com.example.cataloguemovie.Movies.Data


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductionCountry(
    @SerializedName("iso_3166_1")
    var iso31661: String?,
    @SerializedName("name")
    var name: String?
) : Parcelable