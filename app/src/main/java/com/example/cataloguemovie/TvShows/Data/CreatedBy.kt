package com.example.cataloguemovie.TvShows.Data


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CreatedBy(
    @SerializedName("credit_id")
    var creditId: String?,
    @SerializedName("gender")
    var gender: Int?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("profile_path")
    var profilePath: String?
) : Parcelable