package com.example.android.sub1.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tvShow")
data class TvShowModel (

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    @SerializedName("id")
    var id: Int,

    @ColumnInfo(name = "title")
    @SerializedName("original_name")
    var title: String?,

    @ColumnInfo(name = "lang")
    @SerializedName("original_language")
    var lang: String?,

    @ColumnInfo(name = "vote")
    @SerializedName("vote_average")
    var vote: Double?,

    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    var overview: String?,

    @ColumnInfo(name = "release")
    @SerializedName("first_air_date")
    var release: String?,

    @ColumnInfo(name = "image")
    @SerializedName("poster_path")
    var image: String?,

    @ColumnInfo(name = "favorite")
    var favorite: Boolean = false
)