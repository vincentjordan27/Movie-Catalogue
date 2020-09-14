package com.example.android.sub1.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.android.sub1.data.CatalogueRepository
import com.example.android.sub1.data.source.local.entity.MovieModel
import com.example.android.sub1.data.source.local.entity.TvShowModel
import com.example.android.sub1.ui.favorite.FavoriteViewModel
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelTest {

    private lateinit var viewModel : FavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var movieObserver: Observer<PagedList<MovieModel>>

    @Mock
    private lateinit var tvShowObserver: Observer<PagedList<TvShowModel>>

    @Mock
    private lateinit var moviePagedList: PagedList<MovieModel>

    @Mock
    private lateinit var tvShowPagedList: PagedList<TvShowModel>

    @Before
    fun setUp(){
        viewModel = FavoriteViewModel(catalogueRepository)
    }

    @Test
    fun getFavMovie(){
        val dummyFavMovies = moviePagedList
        Mockito.`when`(dummyFavMovies.size).thenReturn(5)
        val movie = MutableLiveData<PagedList<MovieModel>>()
        movie.value = dummyFavMovies

        Mockito.`when`(catalogueRepository.getFavMovies()).thenReturn(movie)
        val movieEntities = viewModel.getFavMovie().value
        verify(catalogueRepository).getFavMovies()
        assertNotNull(movieEntities)
        assertEquals(5, movieEntities?.size)

        viewModel.getFavMovie().observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyFavMovies)

    }

    @Test
    fun getFavTvShow(){
        val dummyFavTvShow = tvShowPagedList
        Mockito.`when`(dummyFavTvShow.size).thenReturn(5)
        val tvShow = MutableLiveData<PagedList<TvShowModel>>()
        tvShow.value = dummyFavTvShow

        Mockito.`when`(catalogueRepository.getFavTvShow()).thenReturn(tvShow)
        val tvShowEntities = viewModel.getFavTvShow().value
        verify(catalogueRepository).getFavTvShow()
        assertNotNull(tvShowEntities)
        assertEquals(5, tvShowEntities?.size)

        viewModel.getFavTvShow().observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(dummyFavTvShow)
    }

}