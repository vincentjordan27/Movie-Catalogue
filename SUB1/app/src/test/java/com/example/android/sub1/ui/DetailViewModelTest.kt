package com.example.android.sub1.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.android.sub1.data.CatalogueRepository
import com.example.android.sub1.data.source.local.entity.MovieModel
import com.example.android.sub1.data.source.local.entity.TvShowModel
import com.example.android.sub1.ui.detail.DetailViewModel
import com.example.android.sub1.utils.DataDummy
import org.junit.Assert.*

import org.mockito.Mockito.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private val dummyMovie = DataDummy.generateMovieDummy()[0]
    private val dummyTvShow = DataDummy.generateTvShowDummy()[0]
    private val movieId = dummyMovie.id
    private val tvShowId = dummyTvShow.id
    private lateinit var viewModel: DetailViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var movieObserver: Observer<MovieModel>

    @Mock
    private lateinit var tvShowObserver: Observer<TvShowModel>

    @Before
    fun setUp(){
        viewModel = DetailViewModel(catalogueRepository)
    }

    @Test
    fun getMovie(){
        val movie = MutableLiveData<MovieModel>()
        movie.value = dummyMovie

        Mockito.`when`(catalogueRepository.getMovieDetail(movieId)).thenReturn(movie)
        val movieEntity = viewModel.getMovieData(movieId).value as MovieModel
        verify(catalogueRepository).getMovieDetail(movieId)
        assertNotNull(movieEntity)
        assertEquals(dummyMovie.id, movieEntity.id)
        assertEquals(dummyMovie.image, movieEntity.image)
        assertEquals(dummyMovie.lang, movieEntity.lang)
        assertEquals(dummyMovie.overview, movieEntity.overview)
        assertEquals(dummyMovie.release, movieEntity.release)
        assertEquals(dummyMovie.title, movieEntity.title)
        assertEquals(dummyMovie.vote, movieEntity.vote)

        viewModel.getMovieData(movieId).observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovie)
    }

    @Test
    fun getTvShow(){
        val tvShow = MutableLiveData<TvShowModel>()
        tvShow.value = dummyTvShow

        Mockito.`when`(catalogueRepository.getTvShowDetail(tvShowId)).thenReturn(tvShow)
        val tvShowEntities = viewModel.getTvShowData(tvShowId).value as TvShowModel
        verify(catalogueRepository).getTvShowDetail(tvShowId)
        assertNotNull(tvShowEntities)
        assertEquals(dummyTvShow.id, tvShowEntities.id)
        assertEquals(dummyTvShow.image, tvShowEntities.image)
        assertEquals(dummyTvShow.lang, tvShowEntities.lang)
        assertEquals(dummyTvShow.overview, tvShowEntities.overview)
        assertEquals(dummyTvShow.release, tvShowEntities.release)
        assertEquals(dummyTvShow.title, tvShowEntities.title)
        assertEquals(dummyTvShow.vote, tvShowEntities.vote)

        viewModel.getTvShowData(tvShowId).observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(dummyTvShow)
    }

}