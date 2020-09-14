package com.example.android.sub1.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.example.android.sub1.data.source.local.entity.MovieModel
import com.example.android.sub1.data.source.local.entity.TvShowModel
import com.example.android.sub1.data.source.local.room.MovieDao
import com.example.android.sub1.data.source.local.room.TvDao

class LocalDataSource private constructor(private val tvDao: TvDao, private val movieDao: MovieDao){

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(tvDao: TvDao, movieDao: MovieDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(tvDao, movieDao)
    }

    fun getAllMovies(): DataSource.Factory<Int, MovieModel> = movieDao.getAllMovie()

    fun getMovieFavorite(): DataSource.Factory<Int, MovieModel> = movieDao.getMovieFavorite()

    fun getMovieItem(id: Int): LiveData<MovieModel> = movieDao.getMovieItem(id)

    fun insertMovie(list: List<MovieModel>) = movieDao.insertMovie(list)

    fun updateMovie(movieModel: MovieModel, state:Boolean) {
        movieModel.favorite = state
        movieDao.updateMovie(movieModel)
    }



    fun getAllTvShow(): DataSource.Factory<Int, TvShowModel> = tvDao.getAllTvShow()

    fun getTvShowFavorite(): DataSource.Factory<Int, TvShowModel> = tvDao.getTvShowFavorite()

    fun getTvShowItem(id: Int): LiveData<TvShowModel> = tvDao.getTvShowItem(id)

    fun insertTvShow(list: List<TvShowModel>) = tvDao.insertTvShow(list)

    fun updateTvShow(tvShowModel: TvShowModel,state: Boolean) {
        tvShowModel.favorite = state
        tvDao.updateTvShow(tvShowModel)
    }
}