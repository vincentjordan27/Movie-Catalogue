package com.example.android.sub1.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.android.sub1.data.CatalogueRepository
import com.example.android.sub1.data.source.local.entity.MovieModel
import com.example.android.sub1.data.source.local.entity.TvShowModel

class FavoriteViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {
    fun getFavMovie() : LiveData<PagedList<MovieModel>> {
        return catalogueRepository.getFavMovies()
    }

    fun getFavTvShow() : LiveData<PagedList<TvShowModel>> {
        return catalogueRepository.getFavTvShow()
    }
}