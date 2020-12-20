package com.dorizu.marvel.core.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dorizu.marvel.core.data.source.local.entity.ComicEntity

@Database(entities = [ComicEntity::class], version = 1, exportSchema = false)
abstract class ComicDatabase: RoomDatabase() {
    abstract fun comicDao(): ComicDao

    companion object{
        @Volatile
        private var INSTANCE: ComicDatabase? = null

        fun getInstance(contex: Context): ComicDatabase =
            INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    contex.applicationContext,
                    ComicDatabase::class.java,
                    "Comic.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
    }
}