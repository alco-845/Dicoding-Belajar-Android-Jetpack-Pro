package com.alcorp.projectacademyapp.ui.detail

import com.alcorp.projectacademyapp.utils.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class DetailCourseViewModelTest {

    private lateinit var viewModel: DetailCourseViewModel
    private val dummyCourse = DataDummy.generateDummyCourses()[0]
    private val courseId = dummyCourse.courseId

    @Before
    fun setUp(){
        viewModel = DetailCourseViewModel()
        viewModel.setSelectedCourse(courseId)
    }

    @Test
    fun getCourse() {
        viewModel.setSelectedCourse(dummyCourse.courseId)
        val courseEntity = viewModel.getCourse()
        assertNotNull(dummyCourse.courseId, courseEntity.courseId)
        assertNotNull(dummyCourse.deadline, courseEntity.deadline)
        assertNotNull(dummyCourse.description, courseEntity.description)
        assertNotNull(dummyCourse.imagePath, courseEntity.imagePath)
        assertNotNull(dummyCourse.title, courseEntity.title)
    }

    @Test
    fun getModules(){
        val moduleEntities = viewModel.getModules()
        assertNotNull(moduleEntities)
        assertEquals(7, moduleEntities.size.toLong())
    }
}