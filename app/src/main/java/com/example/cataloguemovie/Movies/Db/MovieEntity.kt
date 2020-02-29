package com.example.cataloguemovie.Movies.Db


import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "MoviesTable")
data class MovieEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int?,
    @ColumnInfo(name = "backdropPath")
    var backdropPath: String?,
    @ColumnInfo(name = "budget")
    var budget: Int?,
    @ColumnInfo(name = "genres")
    var genres: String?,
    @ColumnInfo(name = "homepage")
    var homepage: String?,
    @ColumnInfo(name = "originalLanguage")
    var originalLanguage: String?,
    @ColumnInfo(name = "originalTitle")
    var originalTitle: String?,
    @ColumnInfo(name = "overview")
    var overview: String?,
    @ColumnInfo(name = "popularity")
    var popularity: Double?,
    @ColumnInfo(name = "releaseDate")
    var releaseDate: String?,
    @ColumnInfo(name = "revenue")
    var revenue: Int?,
    @ColumnInfo(name = "runtime")
    var runtime: Int?,
    @ColumnInfo(name = "spokenLanguages")
    var spokenLanguages: String?,
    @ColumnInfo(name = "status")
    var status: String?,
    @ColumnInfo(name = "voteAverage")
    var voteAverage: Float?
) : Parcelable