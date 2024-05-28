package com.alcorp.moviecatalogue.utils

import com.alcorp.moviecatalogue.data.source.remote.response.MovieDetail
import com.alcorp.moviecatalogue.data.source.remote.response.MovieResponse
import com.alcorp.moviecatalogue.data.source.remote.response.TvDetail
import com.alcorp.moviecatalogue.data.source.remote.response.TvResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("movie/top_rated?api_key=c8657156216a4b84102c0be09a148962")
    fun loadListMovie(): Call<MovieResponse>
    
    @GET("movie/{id}?api_key=c8657156216a4b84102c0be09a148962")
    fun loadMovie(@Path("id") id: String): Call<MovieDetail>

    @GET("tv/popular?api_key=c8657156216a4b84102c0be09a148962")
    fun loadListTv(): Call<TvResponse>

    @GET("tv/{id}?api_key=c8657156216a4b84102c0be09a148962")
    fun loadTv(@Path("id") id: String): Call<TvDetail>
}