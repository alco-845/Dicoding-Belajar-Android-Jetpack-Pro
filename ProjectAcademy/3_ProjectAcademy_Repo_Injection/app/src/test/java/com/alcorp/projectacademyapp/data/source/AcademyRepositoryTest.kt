package com.alcorp.projectacademyapp.data.source

import com.alcorp.projectacademyapp.data.source.remote.RemoteDataSource
import com.alcorp.projectacademyapp.data.source.remote.response.ContentResponse
import com.alcorp.projectacademyapp.data.source.remote.response.CourseResponse
import com.alcorp.projectacademyapp.data.source.remote.response.ModuleResponse
import com.alcorp.projectacademyapp.utils.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.mockito.Mockito.*

class AcademyRepositoryTest {
    private val remote = mock(RemoteDataSource::class.java)
    private val academyRepository = FakeAcademyRepository(remote)

    private val courseResponse = DataDummy.generateRemoteDummyCourses()
    private val courseId = courseResponse[0].id
    private val moduleResponses = DataDummy.generateRemoteDummyModules(courseId)
    private val moduleId = moduleResponses[0].moduleId
    private val content = DataDummy.generateRemoteDummyContent(moduleId)

    @Test
    fun getAllCourses(){
        `when`<List<CourseResponse>>(remote.getAllCourses()).thenReturn(courseResponse)
        val courseEntities = academyRepository.getAllCourses()
        verify<RemoteDataSource>(remote).getAllCourses()
        assertNotNull(courseEntities)
        assertEquals(courseResponse.size.toLong(), courseEntities.size.toLong())
    }

    @Test
    fun getAllModulesByCourses(){
        `when`<List<ModuleResponse>>(remote.getModules(courseId)).thenReturn(moduleResponses)
        val moduleEntities = academyRepository.getAllModulesByCourse(courseId)
        verify<RemoteDataSource>(remote).getModules(courseId)
        assertNotNull(moduleEntities)
        assertEquals(moduleResponses.size.toLong(), moduleEntities.size.toLong())
    }

    @Test
    fun getBookmarkedCourses(){
        `when`<List<CourseResponse>>(remote.getAllCourses()).thenReturn(courseResponse)
        val courseEntities = academyRepository.getBookmarkedCourses()
        verify<RemoteDataSource>(remote).getAllCourses()
        assertNotNull(courseEntities)
        assertEquals(courseResponse.size.toLong(), courseEntities.size.toLong())
    }

    @Test
    fun getContent(){
        `when`<List<ModuleResponse>>(remote.getModules(courseId)).thenReturn(moduleResponses)
        `when`<ContentResponse>(remote.getContent(moduleId)).thenReturn(content)
        val resultModule = academyRepository.getContent(courseId, moduleId)
        verify<RemoteDataSource>(remote).getContent(moduleId)
        assertNotNull(resultModule)
        assertEquals(content.content, resultModule.contentEntity?.content)
    }

    @Test
    fun getCourseWithModules(){
        `when`<List<CourseResponse>>(remote.getAllCourses()).thenReturn(courseResponse)
        val resultCourse = academyRepository.getCourseWithModules(courseId)
        verify<RemoteDataSource>(remote).getAllCourses()
        assertNotNull(resultCourse)
        assertEquals(courseResponse[0].title, resultCourse.title)
    }
}