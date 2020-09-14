package com.example.android.sub1.data.source.remote.response

import com.example.android.sub1.data.source.local.entity.TvShowModel
import com.google.gson.annotations.SerializedName

data class TvShowResponse (
   @SerializedName("results")
   val results : List<TvShowModel>
)