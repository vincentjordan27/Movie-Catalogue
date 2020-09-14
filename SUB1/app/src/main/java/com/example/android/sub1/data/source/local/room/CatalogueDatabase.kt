package com.example.android.sub1.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.android.sub1.data.source.local.entity.MovieModel
import com.example.android.sub1.data.source.local.entity.TvShowModel

@Database(entities = [MovieModel::class, TvShowModel::class],
        version = 1,
        exportSchema = false)
abstract class CatalogueDatabase : RoomDatabase(){
    abstract fun tvShowDao(): TvDao
    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var INSTANCE: CatalogueDatabase? = null

        fun getInstance(context: Context): CatalogueDatabase =
            INSTANCE ?: synchronized(this){
                INSTANCE ?: Room.databaseBuilder(context.applicationContext,
                CatalogueDatabase::class.java,
                "Catalogue.db").build()
            }
    }

}