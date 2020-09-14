package com.example.android.sub1.data.source.remote.response

import com.example.android.sub1.data.source.local.entity.MovieModel
import com.google.gson.annotations.SerializedName


data class MovieResponse (
    @SerializedName("results")
    val results: List<MovieModel>
)