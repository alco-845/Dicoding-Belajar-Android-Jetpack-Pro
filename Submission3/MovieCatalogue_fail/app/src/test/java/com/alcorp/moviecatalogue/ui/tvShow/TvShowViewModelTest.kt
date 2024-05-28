package com.alcorp.moviecatalogue.ui.tvShow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.alcorp.moviecatalogue.data.source.local.entity.TvShowEntity
import com.alcorp.moviecatalogue.data.AppRepository
import com.alcorp.moviecatalogue.utils.DataDummy
import junit.framework.TestCase.assertNotNull
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var appRepository: AppRepository

    @Mock
    private lateinit var observer: Observer<List<TvShowEntity>>

    @Before
    fun setUp(){
        viewModel = TvShowViewModel(appRepository)
    }

    @Test
    fun getMovie() {
        val tvData = DataDummy.generateDummyTvShow()
        val tv = MutableLiveData<List<TvShowEntity>>()
        tv.value = tvData

        Mockito.`when`(appRepository.getListTv()).thenReturn(tv)
        val tvEntities = viewModel.getListTv().value
        Mockito.verify(appRepository).getListTv()
        assertNotNull(tvEntities)
        assertEquals(10, tvEntities?.size)

        viewModel.getListTv().observeForever(observer)
        Mockito.verify(observer).onChanged(tvData)
    }
}