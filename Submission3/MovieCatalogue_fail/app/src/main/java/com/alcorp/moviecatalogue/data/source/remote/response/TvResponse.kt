package com.alcorp.moviecatalogue.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TvResponse (
        @field:SerializedName("results")
        val results: List<TvResults>
)

data class TvResults (
        @field:SerializedName("id")
        val id: String,

        @field:SerializedName("poster_path")
        val poster_path: String,

        @field:SerializedName("name")
        val name: String,

        @field:SerializedName("overview")
        val overview: String,

        @field:SerializedName("first_air_date")
        val first_air_date: String
)

data class TvDetail (
        @field:SerializedName("id")
        val id: String,

        @field:SerializedName("poster_path")
        val poster_path: String,

        @field:SerializedName("name")
        val name: String,

        @field:SerializedName("overview")
        val overview: String,

        @field:SerializedName("first_air_date")
        val first_air_date: String
)