package com.alcorp.moviecatalogue.view.detail

import androidx.lifecycle.ViewModel
import com.alcorp.moviecatalogue.data.MovieEntity
import com.alcorp.moviecatalogue.data.TvShowEntity
import com.alcorp.moviecatalogue.utils.DataDummy

class DetailViewModel : ViewModel() {
    private lateinit var title : String

    fun setTitle(title: String){
        this.title = title
    }

    fun getMovie() : MovieEntity {
        lateinit var movie: MovieEntity
        val movieEntities = DataDummy.generateDummyMovie()
        for (movieEntity in movieEntities){
            if (movieEntity.title == title){
                movie = movieEntity
            }
        }
        return movie
    }

    fun getTvShow() : TvShowEntity {
        lateinit var tvShow: TvShowEntity
        val tvShowEntities = DataDummy.generateDummyTvShow()
        for (tvShowEntity in tvShowEntities){
            if (tvShowEntity.title == title){
                tvShow = tvShowEntity
            }
        }
        return tvShow
    }
}