package com.example.cataloguemovie.TvShows.Db

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "TvShowTable")

@Parcelize
data class TvShowEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int?,
    @ColumnInfo(name = "backdropPath")
    var backdropPath: String?,
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
    @ColumnInfo(name = " firstAirDate")
    var firstAirDate: String?,
    @ColumnInfo(name = "languages")
    var languages: String?,
    @ColumnInfo(name = "status")
    var status: String?,
    @ColumnInfo(name = "voteAverage")
    var voteAverage: Double?,
    @ColumnInfo(name = "genre")
    var genre: String?,
    @ColumnInfo(name = "episoderuntime")
    var episodeRuntime: String?,
    @ColumnInfo(name = "name")
    var name: String?,
    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean?
) : Parcelable