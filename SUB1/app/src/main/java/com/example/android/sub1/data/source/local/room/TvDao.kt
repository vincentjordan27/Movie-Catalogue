package com.example.android.sub1.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.example.android.sub1.data.source.local.entity.TvShowModel

@Dao
interface TvDao {

    @Query("SELECT * FROM tvShow")
    fun getAllTvShow(): DataSource.Factory<Int, TvShowModel>

    @Query("SELECT * FROM tvShow WHERE favorite = 1")
    fun getTvShowFavorite(): DataSource.Factory<Int, TvShowModel>

    @Query("SELECT * FROM tvShow WHERE id = :id")
    fun getTvShowItem(id: Int): LiveData<TvShowModel>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShow(list: List<TvShowModel>)

    @Update
    fun updateTvShow(tvShowModel: TvShowModel)
}