package com.alcorp.moviecatalogue.ui.tvShow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.alcorp.moviecatalogue.data.source.local.entity.TvShowEntity
import com.alcorp.moviecatalogue.data.AppRepository

class TvShowViewModel(private val appRepository: AppRepository) : ViewModel() {

    fun getListTv(): LiveData<List<TvShowEntity>> = appRepository.getListTv()
}