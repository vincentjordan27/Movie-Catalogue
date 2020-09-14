package com.example.android.sub1.di

import android.content.Context
import com.example.android.sub1.data.CatalogueRepository
import com.example.android.sub1.data.source.local.LocalDataSource
import com.example.android.sub1.data.source.local.room.CatalogueDatabase
import com.example.android.sub1.data.source.remote.RemoteDataSource
import com.example.android.sub1.utils.AppExecutors

class Injection  {
    companion object {
        fun provideRepository(context: Context): CatalogueRepository {
            val remoteDataSource = RemoteDataSource.getInstance()
            val database = CatalogueDatabase.getInstance(context)
            val localDataSource = LocalDataSource.getInstance(database.tvShowDao(), database.movieDao())
            val appExecutors = AppExecutors()

            return CatalogueRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
        }
    }
}