package com.alcorp.moviecatalogue.view.tvShow

import androidx.lifecycle.ViewModel
import com.alcorp.moviecatalogue.data.TvShowEntity
import com.alcorp.moviecatalogue.utils.DataDummy

class TvShowViewModel : ViewModel() {
    fun getTvShow(): List<TvShowEntity> = DataDummy.generateDummyTvShow()
}