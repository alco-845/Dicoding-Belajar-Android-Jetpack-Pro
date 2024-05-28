package com.alcorp.moviecatalogue.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.alcorp.moviecatalogue.data.MovieEntity
import com.alcorp.moviecatalogue.data.source.AppRepository
import com.alcorp.moviecatalogue.utils.DataDummy
import junit.framework.TestCase.assertNotNull
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var appRepository: AppRepository

    @Mock
    private lateinit var observer: Observer<List<MovieEntity>>

    @Before
    fun setUp(){
        viewModel = MovieViewModel(appRepository)
    }

    @Test
    fun getMovie() {
        val movieData = DataDummy.generateDummyMovie()
        val movie = MutableLiveData<List<MovieEntity>>()
        movie.value = movieData

        `when`(appRepository.getListMovie()).thenReturn(movie)
        val movieEntities = viewModel.getListMovie().value
        verify(appRepository).getListMovie()
        assertNotNull(movieEntities)
        assertEquals(10, movieEntities?.size)

        viewModel.getListMovie().observeForever(observer)
        verify(observer).onChanged(movieData)
    }
}