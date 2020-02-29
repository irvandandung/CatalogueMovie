package com.example.cataloguemovie.Movies.Db

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.cataloguemovie.TvShows.Db.TvShowEntity

@Dao
interface MovieDao {
    @Query("SELECT * from MoviesTable")
    fun alldatamovie(): LiveData<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movieEntity: MovieEntity)

    @Delete
    fun delete(movieEntity: MovieEntity)

    @Query("SELECT * from MoviesTable")
    fun alldatamoviewidget(): List<MovieEntity>

    //for Content Provider
    @Query("SELECT * from MoviesTable")
    fun selectAll(): Cursor
}