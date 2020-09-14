package com.example.android.sub1.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.android.sub1.data.source.local.entity.MovieModel
import com.example.android.sub1.data.source.local.entity.TvShowModel
import com.example.android.sub1.vo.Resource

interface CatalogueDataSource {

    fun getAllMovies(): LiveData<Resource<PagedList<MovieModel>>>
    fun getAllTvShow(): LiveData<Resource<PagedList<TvShowModel>>>
    fun getMovieDetail(id : Int): LiveData<MovieModel>
    fun getTvShowDetail(id: Int): LiveData<TvShowModel>
    fun getFavMovies(): LiveData<PagedList<MovieModel>>
    fun getFavTvShow(): LiveData<PagedList<TvShowModel>>
    fun setFavoriteMovie(movieModel: MovieModel, state: Boolean)
    fun setFavoriteTvShow(tvShowModel: TvShowModel, state: Boolean)
}