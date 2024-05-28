package com.alcorp.moviecatalogue.di

import com.alcorp.moviecatalogue.data.AppRepository
import com.alcorp.moviecatalogue.data.source.remote.RemoteDataSource
import com.alcorp.moviecatalogue.utils.ApiConfig

object Injection {
    fun provideRepository() : AppRepository {
        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig())

        return AppRepository.getInstance(remoteDataSource)
    }
}