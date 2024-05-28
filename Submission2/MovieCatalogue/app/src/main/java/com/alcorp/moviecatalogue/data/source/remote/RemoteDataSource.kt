package com.alcorp.moviecatalogue.data.source.remote

import com.alcorp.moviecatalogue.data.source.remote.response.MovieDetail
import com.alcorp.moviecatalogue.data.source.remote.response.MovieResponse
import com.alcorp.moviecatalogue.data.source.remote.response.TvDetail
import com.alcorp.moviecatalogue.data.source.remote.response.TvResponse
import com.alcorp.moviecatalogue.utils.ApiConfig
import com.alcorp.moviecatalogue.utils.EspressoIdlingResource
import retrofit2.Call

class RemoteDataSource private constructor(private val apiConfig: ApiConfig) {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: ApiConfig): RemoteDataSource =
                instance ?: synchronized(this) {
                    instance ?: RemoteDataSource(helper)
                }
    }

    fun getListMovie(callback: LoadListMovieCallback) {
        EspressoIdlingResource.increment()
        callback.onAllListMovieReceived(apiConfig.getApiService().loadListMovie())
        EspressoIdlingResource.decrement()
    }

    fun getMovie(id: String, callback: LoadMovieCallback) {
        EspressoIdlingResource.increment()
        callback.onAllMovieReceived(apiConfig.getApiService().loadMovie(id))
        EspressoIdlingResource.decrement()
    }

    fun getListTv(callback: LoadListTvCallback) {
        EspressoIdlingResource.increment()
        callback.onAllListTvReceived(apiConfig.getApiService().loadListTv())
        EspressoIdlingResource.decrement()
    }

    fun getTv(id: String, callback: LoadTvCallback) {
        EspressoIdlingResource.increment()
        callback.onAllTvReceived(apiConfig.getApiService().loadTv(id))
        EspressoIdlingResource.decrement()
    }

    interface LoadListMovieCallback {
        fun onAllListMovieReceived(listMovieResponse: Call<MovieResponse>)
    }

    interface LoadMovieCallback {
        fun onAllMovieReceived(movieResponse: Call<MovieDetail>)
    }

    interface LoadListTvCallback {
        fun onAllListTvReceived(listTvResponse: Call<TvResponse>)
    }

    interface LoadTvCallback {
        fun onAllTvReceived(tvResponse: Call<TvDetail>)
    }
}