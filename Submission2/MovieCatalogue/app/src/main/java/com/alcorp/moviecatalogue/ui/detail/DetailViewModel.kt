package com.alcorp.moviecatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.alcorp.moviecatalogue.data.MovieEntity
import com.alcorp.moviecatalogue.data.TvShowEntity
import com.alcorp.moviecatalogue.data.source.AppRepository

class DetailViewModel(private val appRepository: AppRepository) : ViewModel() {

    private lateinit var title : String

    fun setSelected(title: String){
        this.title = title
    }

    fun getDetailMovie(id: String): LiveData<MovieEntity> = appRepository.getMovie(id)

    fun getDetailTv(id: String): LiveData<TvShowEntity> = appRepository.getTv(id)
}