package com.alcorp.moviecatalogue.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.alcorp.moviecatalogue.data.source.local.entity.MovieEntity
import com.alcorp.moviecatalogue.data.source.local.entity.TvShowEntity
import com.alcorp.moviecatalogue.data.source.local.room.AppDao

class LocalDataSource private constructor(private val mAppDao: AppDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(appDao: AppDao): LocalDataSource {
            if (INSTANCE == null) {
                INSTANCE = LocalDataSource(appDao)
            }
            return INSTANCE as LocalDataSource
        }
    }

    fun getMovie(): DataSource.Factory<Int, MovieEntity> = mAppDao.getMovie()

    fun getBookmarkedMovie(): DataSource.Factory<Int, MovieEntity> =
        mAppDao.getBookmarkedMovie()

    fun getMovieById(id: String): LiveData<MovieEntity> =
        mAppDao.getMovieById(id)

    fun getTv(): DataSource.Factory<Int, TvShowEntity> = mAppDao.getTv()

    fun getBookmarkedTv(): DataSource.Factory<Int, TvShowEntity> =
        mAppDao.getBookmarkedTv()

    fun getTvById(id: String): LiveData<TvShowEntity> =
        mAppDao.getTvById(id)
}