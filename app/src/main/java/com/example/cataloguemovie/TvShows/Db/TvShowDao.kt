package com.example.cataloguemovie.TvShows.Db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TvShowDao {
    @Query("SELECT * from TvShowTable")
    fun alldatatvshow(): LiveData<List<TvShowEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(tvshowentity: TvShowEntity)

    @Delete
    fun delete(tvshowentity: TvShowEntity)
}