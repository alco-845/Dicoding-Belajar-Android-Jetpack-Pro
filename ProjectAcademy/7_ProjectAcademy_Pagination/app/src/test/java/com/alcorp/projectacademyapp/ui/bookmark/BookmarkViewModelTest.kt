package com.alcorp.projectacademyapp.ui.bookmark

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.alcorp.projectacademyapp.data.AcademyRepository
import com.alcorp.projectacademyapp.data.source.local.entity.CourseEntity
import org.junit.Assert.assertEquals
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
class BookmarkViewModelTest {

    private lateinit var viewModel: BookmarkViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var academyRepository: AcademyRepository

    @Mock
    private lateinit var observer: Observer<PagedList<CourseEntity>>

    @Mock
    private lateinit var pagedList: PagedList<CourseEntity>

    @Before
    fun setUp(){
        viewModel = BookmarkViewModel(academyRepository)
    }

    @Test
    fun getBookmarks() {
        val dummyCourse = pagedList
        `when`(dummyCourse.size).thenReturn(5)
        val courses = MutableLiveData<PagedList<CourseEntity>>()
        courses.value = dummyCourse

        `when`(academyRepository.getBookmarkedCourses()).thenReturn(courses)
        val courseEntities = viewModel.getBookmarks().value
        verify<AcademyRepository>(academyRepository).getBookmarkedCourses()
        assertNotNull(courseEntities)
        assertEquals(5, courseEntities?.size)

        viewModel.getBookmarks().observeForever(observer)
        verify(observer).onChanged(dummyCourse)
    }
}