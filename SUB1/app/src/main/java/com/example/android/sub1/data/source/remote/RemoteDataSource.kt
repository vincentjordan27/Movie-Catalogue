package com.example.android.sub1.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.sub1.data.source.local.entity.MovieModel
import com.example.android.sub1.data.source.local.entity.TvShowModel
import com.example.android.sub1.data.source.remote.response.MovieResponse
import com.example.android.sub1.data.source.remote.response.TvShowResponse
import com.example.android.sub1.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    private val apiClient = ApiClient.getRetrofitInstance().create(ApiService::class.java)
    private val tag = RemoteDataSource::class.java.simpleName
    companion object {
        fun getInstance(): RemoteDataSource{
            return RemoteDataSource()
        }
    }

    fun getMovies(): LiveData<ApiResponse<List<MovieModel>>> {
        val movies = MutableLiveData<ApiResponse<List<MovieModel>>>()

        EspressoIdlingResource.increment()

        apiClient.movies().enqueue(object : Callback<MovieResponse>{
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
               Log.e(tag, t.message.toString())
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                movies.value = response.body()?.results?.let { ApiResponse.success(it) }
                EspressoIdlingResource.decrement()
            }

        })

        return movies
    }

    fun getTvShow(): LiveData<ApiResponse<List<TvShowModel>>> {
        val tvShow = MutableLiveData<ApiResponse<List<TvShowModel>>>()

        EspressoIdlingResource.increment()

        apiClient.tvShows().enqueue(object: Callback<TvShowResponse>{
            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                Log.e(tag,t.message.toString())
            }

            override fun onResponse(
                call: Call<TvShowResponse>,
                response: Response<TvShowResponse>
            ) {
                tvShow.value = response.body()?.results?.let { ApiResponse.success(it) }
                EspressoIdlingResource.decrement()

            }

        })

        return tvShow
    }
}


