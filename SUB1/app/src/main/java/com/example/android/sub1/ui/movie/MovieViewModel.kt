package com.example.android.sub1.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.android.sub1.data.CatalogueRepository
import com.example.android.sub1.data.source.local.entity.MovieModel
import com.example.android.sub1.vo.Resource

class MovieViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {
    fun getMovieData() : LiveData<Resource<PagedList<MovieModel>>> {
       return catalogueRepository.getAllMovies()
    }

}