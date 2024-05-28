package com.alcorp.moviecatalogue.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.alcorp.moviecatalogue.data.MovieEntity
import com.alcorp.moviecatalogue.data.TvShowEntity
import com.alcorp.moviecatalogue.data.source.AppRepository
import com.alcorp.moviecatalogue.utils.DataDummy
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel
    private val movieData = DataDummy.generateDummyMovie()[0]
    private val tvData = DataDummy.generateDummyTvShow()[9]

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var appRepository: AppRepository

    @Mock
    private lateinit var movieObserver: Observer<MovieEntity>

    @Mock
    private lateinit var tvObserver: Observer<TvShowEntity>

    @Before
    fun setUp(){
        viewModel = DetailViewModel(appRepository)
    }

    @Test
    fun getMovie() {
        val movie = MutableLiveData<MovieEntity>()
        movie.value = movieData

        viewModel.setSelected(movieData.title)
        `when`(appRepository.getMovie(movieData.movieId)).thenReturn(movie)
        val movieEntity = viewModel.getDetailMovie(movieData.movieId).value as MovieEntity
        assertNotNull(movieEntity)
        assertNotNull(movieData.movieId, movieEntity.movieId)
        assertNotNull(movieData.title, movieEntity.title)
        assertNotNull(movieData.yearRelease, movieEntity.yearRelease)
        assertNotNull(movieData.synopsis, movieEntity.synopsis)
        assertNotNull(movieData.imagePath, movieEntity.imagePath)

        viewModel.getDetailMovie(movieData.movieId).observeForever(movieObserver)
        verify(movieObserver).onChanged(movieData)
    }

    @Test
    fun getTvShow() {
        val tv = MutableLiveData<TvShowEntity>()
        tv.value = tvData

        viewModel.setSelected(tvData.title)
        `when`(appRepository.getTv(tvData.tvShowId)).thenReturn(tv)
        val tvEntity = viewModel.getDetailTv(tvData.tvShowId).value as TvShowEntity
        assertNotNull(tvEntity)
        assertNotNull(tvData.tvShowId, tvEntity.tvShowId)
        assertNotNull(tvData.title, tvEntity.title)
        assertNotNull(tvData.yearRelease, tvEntity.yearRelease)
        assertNotNull(tvData.synopsis, tvEntity.synopsis)
        assertNotNull(tvData.imagePath, tvEntity.imagePath)

        viewModel.getDetailTv(tvData.tvShowId).observeForever(tvObserver)
        verify(tvObserver).onChanged(tvData)
    }
}