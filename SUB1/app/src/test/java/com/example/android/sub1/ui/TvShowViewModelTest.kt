package com.example.android.sub1.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.android.sub1.data.CatalogueRepository
import com.example.android.sub1.data.source.local.entity.TvShowModel
import com.example.android.sub1.ui.tvShow.TvShowViewModel
import com.example.android.sub1.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<TvShowModel>>>

    @Mock
    private lateinit var pagedList: PagedList<TvShowModel>

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(catalogueRepository)
    }

    @Test
    fun getTvShow() {
        val dataDummy = Resource.success(pagedList)
        Mockito.`when`(dataDummy.data?.size).thenReturn(5)
        val tvShowData = MutableLiveData<Resource<PagedList<TvShowModel>>>()
        tvShowData.value = dataDummy

        Mockito.`when`(catalogueRepository.getAllTvShow()).thenReturn(tvShowData)
        val tvShowEntities = viewModel.getTvShow().value?.data
        verify(catalogueRepository).getAllTvShow()
        assertNotNull(tvShowEntities)
        assertEquals(5, tvShowEntities?.size)

        viewModel.getTvShow().observeForever(observer)
        verify(observer).onChanged(dataDummy)
    }


}