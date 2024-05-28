package com.alcorp.projectacademyapp.ui.bookmark

import com.alcorp.projectacademyapp.data.CourseEntity
import com.alcorp.projectacademyapp.data.source.AcademyRepository
import com.alcorp.projectacademyapp.utils.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BookmarkViewModelTest {

    private lateinit var viewModel: BookmarkViewModel

    @Mock
    private lateinit var academyRepository: AcademyRepository

    @Before
    fun setUp(){
        viewModel = BookmarkViewModel(academyRepository)
    }

    @Test
    fun getBookmarks() {
        `when`<ArrayList<CourseEntity>>(academyRepository.getBookmarkedCourses()).thenReturn(DataDummy.generateDummyCourses() as ArrayList<CourseEntity>?)
        val courseEntities = viewModel.getBookmarks()
        verify<AcademyRepository>(academyRepository).getBookmarkedCourses()
        assertNotNull(courseEntities)
        assertEquals(5, courseEntities.size)
    }
}