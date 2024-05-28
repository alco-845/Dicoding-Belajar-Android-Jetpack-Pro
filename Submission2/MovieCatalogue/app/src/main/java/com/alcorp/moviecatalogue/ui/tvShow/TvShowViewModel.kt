package com.alcorp.moviecatalogue.ui.tvShow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.alcorp.moviecatalogue.data.TvShowEntity
import com.alcorp.moviecatalogue.data.source.AppRepository

class TvShowViewModel(private val appRepository: AppRepository) : ViewModel() {

    fun getListTv(): LiveData<List<TvShowEntity>> = appRepository.getListTv()
}