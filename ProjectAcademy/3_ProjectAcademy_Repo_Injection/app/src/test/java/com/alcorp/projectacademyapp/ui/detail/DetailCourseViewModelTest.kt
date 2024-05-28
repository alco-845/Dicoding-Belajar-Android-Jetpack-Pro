package com.alcorp.projectacademyapp.ui.detail

import com.alcorp.projectacademyapp.data.ModuleEntity
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
class DetailCourseViewModelTest {

    private lateinit var viewModel: DetailCourseViewModel
    private val dummyCourse = DataDummy.generateDummyCourses()[0]
    private val courseId = dummyCourse.courseId

    @Mock
    private lateinit var academyRepository: AcademyRepository

    @Before
    fun setUp(){
        viewModel = DetailCourseViewModel(academyRepository)
        viewModel.setSelectedCourse(courseId)
    }

    @Test
    fun getCourse() {
        `when`(academyRepository.getCourseWithModules(courseId)).thenReturn(dummyCourse)
        val courseEntity = viewModel.getCourse()
        verify(academyRepository).getCourseWithModules(courseId)
        assertNotNull(dummyCourse.courseId, courseEntity.courseId)
        assertNotNull(dummyCourse.deadline, courseEntity.deadline)
        assertNotNull(dummyCourse.description, courseEntity.description)
        assertNotNull(dummyCourse.imagePath, courseEntity.imagePath)
        assertNotNull(dummyCourse.title, courseEntity.title)
    }

    @Test
    fun getModules(){
        `when`<ArrayList<ModuleEntity>>(academyRepository.getAllModulesByCourse(courseId) as ArrayList<ModuleEntity>?).thenReturn(DataDummy.generateDummyModules(courseId) as ArrayList<ModuleEntity>?)
        val moduleEntities = viewModel.getModules()
        verify<AcademyRepository>(academyRepository).getAllModulesByCourse(courseId)
        assertNotNull(moduleEntities)
        assertEquals(7, moduleEntities.size.toLong())
    }
}