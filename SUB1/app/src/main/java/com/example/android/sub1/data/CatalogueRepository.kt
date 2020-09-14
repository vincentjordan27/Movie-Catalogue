package com.example.android.sub1.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.android.sub1.data.source.local.LocalDataSource
import com.example.android.sub1.data.source.local.entity.MovieModel
import com.example.android.sub1.data.source.local.entity.TvShowModel
import com.example.android.sub1.data.source.remote.ApiResponse
import com.example.android.sub1.data.source.remote.RemoteDataSource
import com.example.android.sub1.utils.AppExecutors
import com.example.android.sub1.vo.Resource

class CatalogueRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors)
    : CatalogueDataSource {
    companion object{
        @Volatile
        private var instance: CatalogueRepository? = null

        fun getInstance(remoteDataSource: RemoteDataSource, localDataSource: LocalDataSource, appExecutors: AppExecutors) : CatalogueRepository =
            instance ?: synchronized(this){
                instance ?: CatalogueRepository(remoteDataSource, localDataSource, appExecutors)
            }
    }

    override fun getAllMovies(): LiveData<Resource<PagedList<MovieModel>>> {
        return object : NetworkBoundResource<PagedList<MovieModel>, List<MovieModel>>(appExecutors){
            override fun loadFromDB(): LiveData<PagedList<MovieModel>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieModel>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<MovieModel>>> {
                return remoteDataSource.getMovies()
            }

            override fun saveCallResult(data: List<MovieModel>) {
                localDataSource.insertMovie(data)
            }

        }.asLiveData()
    }

    override fun getAllTvShow(): LiveData<Resource<PagedList<TvShowModel>>> {
        return object : NetworkBoundResource<PagedList<TvShowModel>, List<TvShowModel>>(appExecutors){
            override fun loadFromDB(): LiveData<PagedList<TvShowModel>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()

                return LivePagedListBuilder(localDataSource.getAllTvShow(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowModel>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<TvShowModel>>> {
                return remoteDataSource.getTvShow()
            }

            override fun saveCallResult(data: List<TvShowModel>) {
                localDataSource.insertTvShow(data)
            }

        }.asLiveData()
    }

    override fun getMovieDetail(id: Int): LiveData<MovieModel> {
       return localDataSource.getMovieItem(id)
    }

    override fun getTvShowDetail(id: Int): LiveData<TvShowModel> {
        return localDataSource.getTvShowItem(id)
    }

    override fun getFavMovies(): LiveData<PagedList<MovieModel>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()

        return LivePagedListBuilder(localDataSource.getMovieFavorite(), config).build()
    }

    override fun getFavTvShow(): LiveData<PagedList<TvShowModel>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()

        return LivePagedListBuilder(localDataSource.getTvShowFavorite(), config).build()
    }

    override fun setFavoriteMovie(movieModel: MovieModel, state:Boolean) {
        appExecutors.diskIO().execute { localDataSource.updateMovie(movieModel,state) }
    }

    override fun setFavoriteTvShow(tvShowModel: TvShowModel, state:Boolean) {
        appExecutors.diskIO().execute { localDataSource.updateTvShow(tvShowModel, state) }
    }


}