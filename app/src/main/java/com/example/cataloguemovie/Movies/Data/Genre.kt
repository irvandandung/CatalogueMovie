package com.example.cataloguemovie.Movies.Data


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Genre(
    @SerializedName("id")
    var id: Int?,
    @SerializedName("name")
    var name: String?
) : Parcelable