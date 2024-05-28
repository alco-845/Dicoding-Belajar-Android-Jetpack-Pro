package com.alcorp.projectacademyapp.di

import android.content.Context
import com.alcorp.projectacademyapp.data.source.AcademyRepository
import com.alcorp.projectacademyapp.data.source.remote.RemoteDataSource
import com.alcorp.projectacademyapp.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context) : AcademyRepository {
        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))

        return AcademyRepository.getInstance(remoteDataSource)
    }
}