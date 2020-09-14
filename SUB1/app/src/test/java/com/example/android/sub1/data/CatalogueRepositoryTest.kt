package com.example.android.sub1.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.android.sub1.data.source.local.LocalDataSource
import com.example.android.sub1.data.source.local.entity.MovieModel
import com.example.android.sub1.data.source.local.entity.TvShowModel
import com.example.android.sub1.data.source.remote.RemoteDataSource
import com.example.android.sub1.utils.AppExecutors
import com.example.android.sub1.utils.DataDummy
import com.example.android.sub1.utils.LiveDataTestUtil
import com.example.android.sub1.utils.PagedListUtil
import com.example.android.sub1.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock

class CatalogueRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)

    private val catalogueRepository = FakeCatalogueRepository(remote,local, appExecutors)

    private val movies = DataDummy.generateMovieDummy()
    private val movie = movies[0]
    private val moviesId = movie.id
    private val tvShows = DataDummy.generateTvShowDummy()
    private val tvShow = tvShows[0]
    private val tvShowId = tvShow.id
    private val favMovies = DataDummy.generateFavMovie()
    private val favTvShow = DataDummy.generateFavTvShow()

    @Test
    fun getAllMovies(){
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieModel>
        Mockito.`when`(local.getAllMovies()).thenReturn(dataSourceFactory)
        catalogueRepository.getAllMovies()

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateMovieDummy()))
        verify(local).getAllMovies()
        assertNotNull(movieEntities.data)
        assertEquals(movies.size.toLong(), movieEntities.data?.size?.toLong())

    }

    @Test
    fun getAllTvShow(){
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowModel>
        Mockito.`when`(local.getAllTvShow()).thenReturn(dataSourceFactory)
        catalogueRepository.getAllTvShow()

        val tvShowEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateTvShowDummy()))
        verify(local).getAllTvShow()
        assertNotNull(tvShowEntities.data)
        assertEquals(tvShows.size.toLong(), tvShowEntities.data?.size?.toLong())
    }

    @Test
    fun getMovieDetail(){
        val dummyMovie = MutableLiveData<MovieModel>()
        dummyMovie.value = DataDummy.generateMovieDummy()[0]
        Mockito.`when`(local.getMovieItem(moviesId)).thenReturn(dummyMovie)

        val movieEntity = LiveDataTestUtil.getValue(catalogueRepository.getMovieDetail(moviesId))
        verify(local).getMovieItem(moviesId)
        assertNotNull(movieEntity)
        assertEquals(movie.id, movieEntity.id)
        assertEquals(movie.favorite, movieEntity.favorite)
        assertEquals(movie.image, movieEntity.image)
        assertEquals(movie.lang, movieEntity.lang)
        assertEquals(movie.overview, movieEntity.overview)
        assertEquals(movie.release, movieEntity.release)
        assertEquals(movie.title, movieEntity.title)
        assertEquals(movie.vote, movieEntity.vote)

    }

    @Test
    fun getTvShowDetail(){
        val dummyTvShow = MutableLiveData<TvShowModel>()
        dummyTvShow.value = DataDummy.generateTvShowDummy()[0]
        Mockito.`when`(local.getTvShowItem(tvShowId)).thenReturn(dummyTvShow)

        val tvShowEntity = LiveDataTestUtil.getValue(catalogueRepository.getTvShowDetail(tvShowId))
        verify(local).getTvShowItem(tvShowId)
        assertNotNull(tvShowEntity)
        assertEquals(tvShow.id, tvShowEntity.id)
        assertEquals(tvShow.favorite, tvShowEntity.favorite)
        assertEquals(tvShow.image, tvShowEntity.image)
        assertEquals(tvShow.lang, tvShowEntity.lang)
        assertEquals(tvShow.overview, tvShowEntity.overview)
        assertEquals(tvShow.release, tvShowEntity.release)
        assertEquals(tvShow.title, tvShowEntity.title)
        assertEquals(tvShow.vote, tvShowEntity.vote)

    }

    @Test
    fun getFavMovies(){
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieModel>
        Mockito.`when`(local.getMovieFavorite()).thenReturn(dataSourceFactory)
        catalogueRepository.getFavMovies()

        val favMoviesEntities = PagedListUtil.mockPagedList(DataDummy.generateFavMovie())
        verify(local).getMovieFavorite()
        assertNotNull(favMoviesEntities)
        assertEquals(favMovies.size.toLong(), favMoviesEntities.size.toLong())
    }

    @Test
    fun getFavTvShows(){
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowModel>
        Mockito.`when`(local.getTvShowFavorite()).thenReturn(dataSourceFactory)
        catalogueRepository.getFavTvShow()

        val favTvShowEntities = PagedListUtil.mockPagedList(DataDummy.generateFavTvShow())
        verify(local).getTvShowFavorite()
        assertNotNull(favTvShowEntities)
        assertEquals(favTvShow.size.toLong(), favTvShowEntities.size.toLong())
    }

}