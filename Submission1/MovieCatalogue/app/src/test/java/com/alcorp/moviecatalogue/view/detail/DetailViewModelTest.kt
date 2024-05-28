package com.alcorp.moviecatalogue.view.detail

import com.alcorp.moviecatalogue.utils.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel
    private val movieData = DataDummy.generateDummyMovie()[0]
    private val tvShowData = DataDummy.generateDummyTvShow()[0]
    private val movieTitle = movieData.title
    private val tvShowTitle = tvShowData.title

    @Before
    fun setUp(){
        viewModel = DetailViewModel()
        viewModel.setTitle(movieTitle)
        viewModel.setTitle(tvShowTitle)
    }

    @Test
    fun getMovie() {
        viewModel.setTitle(movieData.title)
        val movieEntity = viewModel.getMovie()
        assertNotNull(movieData.movieId, movieEntity.movieId)
        assertNotNull(movieData.title, movieEntity.title)
        assertNotNull(movieData.yearRelease, movieEntity.yearRelease)
        assertNotNull(movieData.synopsis, movieEntity.synopsis)
        assertNotNull(movieData.imagePath.toString(), movieEntity.imagePath)
    }

    @Test
    fun getTvShow() {
        viewModel.setTitle(tvShowData.title)
        val tvShowEntity = viewModel.getTvShow()
        assertNotNull(tvShowData.tvShowId, tvShowEntity.tvShowId)
        assertNotNull(tvShowData.title, tvShowEntity.title)
        assertNotNull(tvShowData.yearRelease, tvShowEntity.yearRelease)
        assertNotNull(tvShowData.synopsis, tvShowEntity.synopsis)
        assertNotNull(tvShowData.imagePath.toString(), tvShowEntity.imagePath)
    }
}