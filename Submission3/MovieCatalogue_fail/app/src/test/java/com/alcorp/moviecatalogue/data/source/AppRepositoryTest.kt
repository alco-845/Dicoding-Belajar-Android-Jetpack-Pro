package com.alcorp.moviecatalogue.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.alcorp.moviecatalogue.data.source.remote.RemoteDataSource
import com.alcorp.moviecatalogue.data.source.remote.response.MovieDetail
import com.alcorp.moviecatalogue.data.source.remote.response.MovieResponse
import com.alcorp.moviecatalogue.data.source.remote.response.TvDetail
import com.alcorp.moviecatalogue.data.source.remote.response.TvResponse
import com.alcorp.moviecatalogue.utils.ApiConfig
import com.alcorp.moviecatalogue.utils.DataDummy
import com.alcorp.moviecatalogue.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.any
import org.mockito.Mockito.mock
import retrofit2.Call

class AppRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var apiConfig: ApiConfig

    private val remote = mock(RemoteDataSource::class.java)
    private val appRepository = FakeAppRepository(remote)

    private val movieData = DataDummy.generateDummyMovie()
    private val movieId = movieData[0].id
    private val tvData = DataDummy.generateDummyTvShow()
    private val tvId = tvData[9].id

    @Test
    fun getListMovie() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadListMovieCallback)
                    .onAllListMovieReceived(apiConfig.getApiService().loadListMovie())
            null
        }.`when`(remote).getListMovie(object : RemoteDataSource.LoadListMovieCallback{
            override fun onAllListMovieReceived(listMovieResponse: Call<MovieResponse>) {
                val movieEntity = LiveDataTestUtil.getValue(appRepository.getListMovie())
                verify(remote).getListMovie(any())
                assertNotNull(movieEntity)
                assertEquals(movieData.size.toLong(), movieEntity.size.toLong())
            }
        })
    }

    @Test
    fun getMovie() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMovieCallback)
                    .onAllMovieReceived(apiConfig.getApiService().loadMovie(movieId))
            null
        }.`when`(remote).getMovie(movieId, object : RemoteDataSource.LoadMovieCallback{
            override fun onAllMovieReceived(movieResponse: Call<MovieDetail>) {
                val movieEntity = LiveDataTestUtil.getValue(appRepository.getMovie(movieId))
                verify(remote).getMovie(eq(movieId), any())
                assertNotNull(movieEntity)
                assertNotNull(movieEntity.id)
                assertNotNull(movieEntity.title)
                assertNotNull(movieEntity.release_date)
                assertNotNull(movieEntity.overview)
                assertNotNull(movieEntity.poster_path)
            }
        })
    }

    @Test
    fun getListTv() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadListTvCallback)
                    .onAllListTvReceived(apiConfig.getApiService().loadListTv())
            null
        }.`when`(remote).getListTv(object : RemoteDataSource.LoadListTvCallback{
            override fun onAllListTvReceived(listTvResponse: Call<TvResponse>) {
                val tvEntity = LiveDataTestUtil.getValue(appRepository.getListTv())
                verify(remote).getListTv(any())
                assertNotNull(tvEntity)
                assertEquals(tvData.size.toLong(), tvEntity.size.toLong())
            }
        })
    }

    @Test
    fun getTv() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTvCallback)
                    .onAllTvReceived(apiConfig.getApiService().loadTv(tvId))
            null
        }.`when`(remote).getTv(tvId, object : RemoteDataSource.LoadTvCallback{
            override fun onAllTvReceived(tvResponse: Call<TvDetail>) {
                val tvEntity = LiveDataTestUtil.getValue(appRepository.getTv(tvId))
                verify(remote).getTv(eq(tvId), any())
                assertNotNull(tvEntity)
                assertNotNull(tvEntity.id)
                assertNotNull(tvEntity.name)
                assertNotNull(tvEntity.first_air_date)
                assertNotNull(tvEntity.overview)
                assertNotNull(tvEntity.poster_path)
            }
        })
    }
}