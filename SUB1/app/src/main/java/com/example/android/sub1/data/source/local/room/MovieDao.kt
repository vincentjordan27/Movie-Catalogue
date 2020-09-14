package com.example.android.sub1.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.example.android.sub1.data.source.local.entity.MovieModel

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie")
    fun getAllMovie(): DataSource.Factory<Int, MovieModel>

    @Query("SELECT * FROM movie WHERE favorite = 1")
    fun getMovieFavorite(): DataSource.Factory<Int, MovieModel>

    @Query("SELECT * FROM movie WHERE id = :id")
    fun getMovieItem(id: Int): LiveData<MovieModel>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(list: List<MovieModel>)

    @Update
    fun updateMovie(movieModel: MovieModel)
}