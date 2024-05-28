package com.alcorp.moviecatalogue.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse (
    @field:SerializedName("results")
    val results: List<MovieResults>
)

data class MovieResults (
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("poster_path")
    val poster_path: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("release_date")
    val release_date: String
)

data class MovieDetail (
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("poster_path")
    val poster_path: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("release_date")
    val release_date: String
)