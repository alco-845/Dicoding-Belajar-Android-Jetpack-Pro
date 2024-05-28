package com.alcorp.projectacademyapp.di

import android.content.Context
import com.alcorp.projectacademyapp.data.AcademyRepository
import com.alcorp.projectacademyapp.data.source.local.LocalDataSource
import com.alcorp.projectacademyapp.data.source.local.room.AcademyDatabase
import com.alcorp.projectacademyapp.data.source.remote.RemoteDataSource
import com.alcorp.projectacademyapp.utils.AppExecutors
import com.alcorp.projectacademyapp.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context) : AcademyRepository {

        val database = AcademyDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        val localDataSource = LocalDataSource.getInstance(database.academyDao())
        val appExecutors = AppExecutors()

        return AcademyRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}