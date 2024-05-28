package com.alcorp.moviecatalogue.view.tvShow

import junit.framework.TestCase.assertNotNull
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @Before
    fun setUp(){
        viewModel = TvShowViewModel()
    }

    @Test
    fun getTvShow() {
        val tvShowEntities = viewModel.getTvShow()
        assertNotNull(tvShowEntities)
        assertEquals(10, tvShowEntities.size)
    }
}