package com.example.cataloguemovie.TvShows.Db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(TvShowEntity::class), version = 1)
abstract class RoomTvShowConnect : RoomDatabase() {
    abstract fun tvshowDao(): TvShowDao

    companion object {
        private var INSTANCE: RoomTvShowConnect? = null

        fun getInstance(
            context: Context
        ): RoomTvShowConnect {
            if (INSTANCE == null) {
                synchronized(RoomTvShowConnect::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        RoomTvShowConnect::class.java,
                        "CatalogueTvShowDB"
                    ).build()
                }
            }
            return INSTANCE as RoomTvShowConnect
        }
    }
}