package com.alcorp.moviecatalogue.ui.movie

import androidx.lifecycle.*
import com.alcorp.moviecatalogue.data.source.local.entity.MovieEntity
import com.alcorp.moviecatalogue.data.AppRepository

class MovieViewModel(private val appRepository: AppRepository) : ViewModel() {

    var movie: LiveData<List<MovieEntity>>? = null
        get() {
            if (field == null) {
                field = appRepository.getListMovie()
                getListMovie()
            }
            return field
        }
        private set

    fun getListMovie(): LiveData<List<MovieEntity>> = appRepository.getListMovie()
}