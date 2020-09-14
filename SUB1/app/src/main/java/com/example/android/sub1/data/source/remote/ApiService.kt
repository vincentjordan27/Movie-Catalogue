package com.example.android.sub1.data.source.remote

import com.example.android.sub1.BuildConfig.API_KEY
import com.example.android.sub1.data.source.remote.response.MovieResponse
import com.example.android.sub1.data.source.remote.response.TvShowResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("movie/now_playing?api_key=$API_KEY")
    fun movies(): Call<MovieResponse>

    @GET("tv/airing_today?api_key=$API_KEY")
    fun tvShows(): Call<TvShowResponse>

}