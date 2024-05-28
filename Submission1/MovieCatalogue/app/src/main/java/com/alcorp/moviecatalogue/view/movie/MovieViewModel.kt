package com.alcorp.moviecatalogue.view.movie

import androidx.lifecycle.ViewModel
import com.alcorp.moviecatalogue.data.MovieEntity
import com.alcorp.moviecatalogue.utils.DataDummy

class MovieViewModel : ViewModel() {
    fun getMovie() : List<MovieEntity> = DataDummy.generateDummyMovie()
}