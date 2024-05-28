package com.alcorp.moviecatalogue.utils.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alcorp.moviecatalogue.data.AppRepository
import com.alcorp.moviecatalogue.di.Injection
import com.alcorp.moviecatalogue.ui.detail.DetailViewModel
import com.alcorp.moviecatalogue.ui.movie.MovieViewModel
import com.alcorp.moviecatalogue.ui.tvShow.TvShowViewModel

class ViewModelFactory private constructor(private val mAppRepository: AppRepository) : ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance() : ViewModelFactory =
                instance ?: synchronized(this) {
                    instance ?: ViewModelFactory(Injection.provideRepository())
                }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                return MovieViewModel(mAppRepository) as T
            }

            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> {
                return TvShowViewModel(mAppRepository) as T
            }

            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                return DetailViewModel(mAppRepository) as T
            }

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}