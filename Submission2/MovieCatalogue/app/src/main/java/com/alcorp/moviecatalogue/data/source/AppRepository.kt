package com.alcorp.moviecatalogue.data.source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alcorp.moviecatalogue.data.MovieEntity
import com.alcorp.moviecatalogue.data.TvShowEntity
import com.alcorp.moviecatalogue.data.source.remote.RemoteDataSource
import com.alcorp.moviecatalogue.data.source.remote.response.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AppRepository private constructor(private val remoteDataSource: RemoteDataSource) : AppDataSource {

    private val listMovie = MutableLiveData<List<MovieEntity>>()

    private val listTv = MutableLiveData<List<TvShowEntity>>()

    private val detailMovie = MutableLiveData<MovieEntity>()

    private val detailTv = MutableLiveData<TvShowEntity>()

    companion object {
        @Volatile
        private var instance: AppRepository? = null

        fun getInstance(remoteData: RemoteDataSource) : AppRepository =
                instance ?: synchronized(this) {
                    instance ?: AppRepository(remoteData)
                }
    }

    override fun getListMovie(): LiveData<List<MovieEntity>> {
        val tag = "MovieViewModel"

        remoteDataSource.getListMovie(object : RemoteDataSource.LoadListMovieCallback {
            override fun onAllListMovieReceived(listMovieResponse: Call<MovieResponse>) {
                listMovieResponse.enqueue(object : Callback<MovieResponse> {
                    override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                        if (response.isSuccessful) {
                            val movieList = ArrayList<MovieEntity>()
                            for (res in response.body()?.results!!) {
                                val course = MovieEntity(res.id,
                                        res.title,
                                        res.overview,
                                        res.release_date,
                                        res.poster_path)
                                movieList.add(course)
                            }
                            listMovie.postValue(movieList)
                        } else {
                            Log.e(tag, "onFailure: ${response.message()}")
                        }
                    }

                    override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                        Log.e(tag, "onFailure: ${t.message.toString()}")
                    }
                })
            }
        })
        return listMovie
    }

    override fun getMovie(id: String): LiveData<MovieEntity> {
        val tag = "DetailViewModel"

        remoteDataSource.getMovie(id, object : RemoteDataSource.LoadMovieCallback {
            override fun onAllMovieReceived(movieResponse: Call<MovieDetail>) {
                movieResponse.enqueue(object : Callback<MovieDetail> {
                    override fun onResponse(call: Call<MovieDetail>, response: Response<MovieDetail>) {
                        if (response.isSuccessful) {
                            val detail = MovieEntity(
                                response.body()?.id.toString(),
                                response.body()?.title.toString(),
                                response.body()?.overview.toString(),
                                response.body()?.release_date.toString(),
                                response.body()?.poster_path.toString()
                            )
                            detailMovie.postValue(detail)
                        } else {
                            Log.e(tag, "onFailure: ${response.message()}")
                        }
                    }

                    override fun onFailure(call: Call<MovieDetail>, t: Throwable) {
                        Log.e(tag, "onFailure: ${t.message.toString()}")
                    }
                })
            }
        })
        return detailMovie
    }

    override fun getListTv(): LiveData<List<TvShowEntity>> {
        val tag = "TvViewModel"

        remoteDataSource.getListTv(object : RemoteDataSource.LoadListTvCallback {
            override fun onAllListTvReceived(listTvResponse: Call<TvResponse>) {
                listTvResponse.enqueue(object : Callback<TvResponse> {
                    override fun onResponse(call: Call<TvResponse>, response: Response<TvResponse>) {
                        if (response.isSuccessful) {
                            val tvList = ArrayList<TvShowEntity>()
                            for (res in response.body()?.results!!) {
                                val course = TvShowEntity(res.id,
                                        res.name,
                                        res.overview,
                                        res.first_air_date,
                                        res.poster_path)
                                tvList.add(course)
                            }
                            listTv.postValue(tvList)
                        } else {
                            Log.e(tag, "onFailure: ${response.message()}")
                        }
                    }

                    override fun onFailure(call: Call<TvResponse>, t: Throwable) {
                        Log.e(tag, "onFailure: ${t.message.toString()}")
                    }
                })
            }
        })
        return listTv
    }

    override fun getTv(id: String): LiveData<TvShowEntity> {
        val tag = "DetailViewModel"

        remoteDataSource.getTv(id, object : RemoteDataSource.LoadTvCallback {
            override fun onAllTvReceived(tvResponse: Call<TvDetail>) {
                tvResponse.enqueue(object : Callback<TvDetail> {
                    override fun onResponse(call: Call<TvDetail>, response: Response<TvDetail>) {
                        if (response.isSuccessful) {
                            val detail = TvShowEntity(
                                response.body()?.id.toString(),
                                response.body()?.name.toString(),
                                response.body()?.overview.toString(),
                                response.body()?.first_air_date.toString(),
                                response.body()?.poster_path.toString()
                            )
                            detailTv.postValue(detail)
                        } else {
                            Log.e(tag, "onFailure: ${response.message()}")
                        }
                    }

                    override fun onFailure(call: Call<TvDetail>, t: Throwable) {
                        Log.e(tag, "onFailure: ${t.message.toString()}")
                    }
                })
            }
        })
        return detailTv
    }
}