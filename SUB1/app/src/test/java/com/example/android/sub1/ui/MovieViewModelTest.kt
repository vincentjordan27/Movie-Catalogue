package com.example.android.sub1.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.android.sub1.data.CatalogueRepository
import com.example.android.sub1.data.source.local.entity.MovieModel
import com.example.android.sub1.ui.movie.MovieViewModel
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
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<MovieModel>>>

    @Mock
    private lateinit var pagedList: PagedList<MovieModel>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(catalogueRepository)
    }

    @Test
    fun getMovie() {
        val dummyMovie = Resource.success(pagedList)
        Mockito.`when`(dummyMovie.data?.size).thenReturn(5)
        val movie = MutableLiveData<Resource<PagedList<MovieModel>>>()
        movie.value = dummyMovie

        Mockito.`when`(catalogueRepository.getAllMovies()).thenReturn(movie)
        val movieEntities = viewModel.getMovieData().value?.data
        verify(catalogueRepository).getAllMovies()
        assertNotNull(movieEntities)
        assertEquals(5, movieEntities?.size)

        viewModel.getMovieData().observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }


}