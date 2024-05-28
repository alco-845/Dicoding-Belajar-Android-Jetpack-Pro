package com.alcorp.moviecatalogue.view.movie

import junit.framework.TestCase.assertNotNull
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @Before
    fun setUp(){
        viewModel = MovieViewModel()
    }

    @Test
    fun getMovie() {
        val movieEntities = viewModel.getMovie()
        assertNotNull(movieEntities)
        assertEquals(10, movieEntities.size)
    }
}