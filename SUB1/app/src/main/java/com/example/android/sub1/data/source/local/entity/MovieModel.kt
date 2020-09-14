package com.example.android.sub1.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "movie")
data class MovieModel (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    @SerializedName("id")
    var id: Int,

    @ColumnInfo(name = "title")
    @SerializedName("original_title")
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
    @SerializedName("release_date")
    var release: String?,

    @ColumnInfo(name = "image")
    @SerializedName("poster_path")
    var image: String?,

    @ColumnInfo(name = "favorite")
    var favorite: Boolean = false
)