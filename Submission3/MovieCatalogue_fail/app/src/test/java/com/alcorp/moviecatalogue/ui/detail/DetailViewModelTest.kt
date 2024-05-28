package com.alcorp.moviecatalogue.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.alcorp.moviecatalogue.data.source.local.entity.MovieEntity
import com.alcorp.moviecatalogue.data.source.local.entity.TvShowEntity
import com.alcorp.moviecatalogue.data.AppRepository
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
        `when`(appRepository.getMovie(movieData.id)).thenReturn(movie)
        val movieEntity = viewModel.getDetailMovie(movieData.id).value as MovieEntity
        assertNotNull(movieEntity)
        assertNotNull(movieData.id, movieEntity.id)
        assertNotNull(movieData.title, movieEntity.title)
        assertNotNull(movieData.release_date, movieEntity.release_date)
        assertNotNull(movieData.overview, movieEntity.overview)
        assertNotNull(movieData.poster_path, movieEntity.poster_path)

        viewModel.getDetailMovie(movieData.id).observeForever(movieObserver)
        verify(movieObserver).onChanged(movieData)
    }

    @Test
    fun getTvShow() {
        val tv = MutableLiveData<TvShowEntity>()
        tv.value = tvData

        viewModel.setSelected(tvData.name)
        `when`(appRepository.getTv(tvData.id)).thenReturn(tv)
        val tvEntity = viewModel.getDetailTv(tvData.id).value as TvShowEntity
        assertNotNull(tvEntity)
        assertNotNull(tvData.id, tvEntity.id)
        assertNotNull(tvData.name, tvEntity.name)
        assertNotNull(tvData.first_air_date, tvEntity.first_air_date)
        assertNotNull(tvData.overview, tvEntity.overview)
        assertNotNull(tvData.poster_path, tvEntity.poster_path)

        viewModel.getDetailTv(tvData.id).observeForever(tvObserver)
        verify(tvObserver).onChanged(tvData)
    }
}