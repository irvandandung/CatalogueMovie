package com.example.favoritemovie.Model

import android.database.Cursor
import android.os.Parcelable
import com.example.favoritemovie.Contract.DatabaseContract
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FavoriteMovie(
    var id: Int,
    var popularity: Double = 0.0,
    var poster: String = "",
    var title: String = "",
    var voteAverage: Double = 0.0,
    var overview: String = "",
    var releaseDate: String = ""
):Parcelable{
    companion object{
        fun fromCursor(cursor: Cursor?): List<FavoriteMovie> {
            val favoritMovieList = arrayListOf<FavoriteMovie>()
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    val movieId = cursor.getInt(
                        cursor.getColumnIndex(DatabaseContract.FavoriteMoviesColumn.MOVIE_ID)
                    )
                    val popularity = cursor.getDouble(
                        cursor.getColumnIndex(DatabaseContract.FavoriteMoviesColumn.POPULARITY)
                    )
                    val poster = cursor.getString(
                        cursor.getColumnIndex(DatabaseContract.FavoriteMoviesColumn.POSTER)
                    )
                    val title = cursor.getString(
                        cursor.getColumnIndex(DatabaseContract.FavoriteMoviesColumn.TITLE)
                    )
                    val voteAverage = cursor.getDouble(
                        cursor.getColumnIndex(DatabaseContract.FavoriteMoviesColumn.VOTE_AVERANGE)
                    )
                    val overview = cursor.getString(
                        cursor.getColumnIndex(DatabaseContract.FavoriteMoviesColumn.OVERVIEW)
                    )
                    val releaseDate = cursor.getString(
                        cursor.getColumnIndex(DatabaseContract.FavoriteMoviesColumn.RELEASE_DATE)
                    )
                    favoritMovieList.add(
                        FavoriteMovie(
                            movieId,
                            popularity,
                            poster,
                            title,
                            voteAverage,
                            overview,
                            releaseDate
                        )
                    )
                }
            }
            return favoritMovieList
        }
    }
}