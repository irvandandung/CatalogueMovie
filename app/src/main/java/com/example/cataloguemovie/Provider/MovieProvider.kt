package com.example.cataloguemovie.Provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.example.cataloguemovie.Movies.Db.RoomMovieConnect
import com.example.cataloguemovie.Provider.DatabaseContract.Companion.AUTHORITY
import com.example.cataloguemovie.Provider.DatabaseContract.FavoriteMoviesColumn.Companion.TABLE_FAVORITE_MOVIE

class MovieProvider : ContentProvider() {

    companion object {
        private const val FAVORITE_MOVIE = 1

        private val sUriMatcher = UriMatcher(UriMatcher.NO_MATCH)
        init {
            sUriMatcher.addURI(AUTHORITY, TABLE_FAVORITE_MOVIE, FAVORITE_MOVIE)
        }
    }
    override fun delete(p0: Uri, p1: String?, p2: Array<out String>?): Int = 0

    override fun getType(p0: Uri): String? = null

    override fun insert(p0: Uri, p1: ContentValues?): Uri? = null

    override fun update(p0: Uri, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int = 0

    //We use ROOM so no need create. just return true
    override fun onCreate(): Boolean = true

    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? {
        return when(sUriMatcher.match(uri)){
            FAVORITE_MOVIE -> {
                val context = context ?: return null
                val movieDao = RoomMovieConnect.getInstance(context)?.movieDao()
                val cursor = movieDao?.selectAll()
                cursor?.setNotificationUri(context.contentResolver, uri)
                cursor
            }
            else -> return null
        }
    }
}
