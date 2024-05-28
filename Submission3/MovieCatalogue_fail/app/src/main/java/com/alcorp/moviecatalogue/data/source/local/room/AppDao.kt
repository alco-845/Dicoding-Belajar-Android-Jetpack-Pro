package com.alcorp.moviecatalogue.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.alcorp.moviecatalogue.data.source.local.entity.MovieEntity
import com.alcorp.moviecatalogue.data.source.local.entity.TvShowEntity

@Dao
interface AppDao {
    @Query("SELECT * FROM movieentities")
    fun getMovie(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM movieentities where bookmarked = 1")
    fun getBookmarkedMovie(): DataSource.Factory<Int, MovieEntity>

    @Transaction
    @Query("SELECT * FROM movieentities WHERE id = :id")
    fun getMovieById(id: String): LiveData<MovieEntity>

    @Query("SELECT * FROM tventities")
    fun getTv(): DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM tventities where bookmarked = 1")
    fun getBookmarkedTv(): DataSource.Factory<Int, TvShowEntity>

    @Transaction
    @Query("SELECT * FROM tventities WHERE id = :id")
    fun getTvById(id: String): LiveData<TvShowEntity>
}