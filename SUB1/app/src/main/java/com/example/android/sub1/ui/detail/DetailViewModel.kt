package com.example.android.sub1.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.android.sub1.data.CatalogueRepository
import com.example.android.sub1.data.source.local.entity.MovieModel
import com.example.android.sub1.data.source.local.entity.TvShowModel

class DetailViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {
    fun getMovieData(id: Int) : LiveData<MovieModel>{
        return catalogueRepository.getMovieDetail(id)
    }

    fun getTvShowData(id : Int) : LiveData<TvShowModel>{
        return catalogueRepository.getTvShowDetail(id)
    }

    fun setFavoriteMovie(movieModel: MovieModel, state: Boolean) {
        catalogueRepository.setFavoriteMovie(movieModel, state)
    }

    fun setFavoriteTvShow(tvShowModel: TvShowModel, state: Boolean){
        catalogueRepository.setFavoriteTvShow(tvShowModel, state)
    }

}