package com.example.android.sub1.ui.tvShow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.android.sub1.data.CatalogueRepository
import com.example.android.sub1.data.source.local.entity.TvShowModel
import com.example.android.sub1.vo.Resource

class TvShowViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {
    fun getTvShow() : LiveData<Resource<PagedList<TvShowModel>>> =
         catalogueRepository.getAllTvShow()

}