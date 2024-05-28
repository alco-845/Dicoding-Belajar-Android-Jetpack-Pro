package com.alcorp.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import com.alcorp.moviecatalogue.data.MovieEntity
import com.alcorp.moviecatalogue.data.TvShowEntity

interface AppDataSource {

    fun getListMovie() : LiveData<List<MovieEntity>>

    fun getMovie(id: String) : LiveData<MovieEntity>

    fun getListTv() : LiveData<List<TvShowEntity>>

    fun getTv(id: String) : LiveData<TvShowEntity>
}