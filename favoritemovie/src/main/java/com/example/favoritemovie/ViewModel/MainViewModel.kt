package com.example.favoritemovie.ViewModel

import android.content.ContentResolver
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.favoritemovie.Contract.DatabaseContract
import com.example.favoritemovie.Model.FavoriteMovie

class MainViewModel (private val contentResolver: ContentResolver):ViewModel(){
    val favoriteMovie: LiveData<List<FavoriteMovie>> = liveData{
        val cursor = contentResolver.query(
            DatabaseContract.FavoriteMoviesColumn.CONTENT_URI,
            null,
            null,
            null,
            null
        )
        emit(FavoriteMovie.fromCursor(cursor))
        cursor?.close()
    }
}