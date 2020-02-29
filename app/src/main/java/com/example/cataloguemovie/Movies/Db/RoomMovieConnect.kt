package com.example.cataloguemovie.Movies.Db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(MovieEntity::class), version = 1)
abstract class RoomMovieConnect : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        private var INSTANCE: RoomMovieConnect? = null

        fun getInstance(context: Context)
                : RoomMovieConnect? {
            if (INSTANCE == null) {
                synchronized(RoomMovieConnect::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        RoomMovieConnect::class.java,
                        "CatalogueMovieDB"
                    ).build()
                }
            }
            return INSTANCE as RoomMovieConnect
        }
    }
}