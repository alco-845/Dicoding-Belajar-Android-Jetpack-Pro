package com.alcorp.moviecatalogue.ui.movie

import androidx.lifecycle.*
import com.alcorp.moviecatalogue.data.MovieEntity
import com.alcorp.moviecatalogue.data.source.AppRepository

class MovieViewModel(private val appRepository: AppRepository) : ViewModel() {

    fun getListMovie(): LiveData<List<MovieEntity>> = appRepository.getListMovie()
}