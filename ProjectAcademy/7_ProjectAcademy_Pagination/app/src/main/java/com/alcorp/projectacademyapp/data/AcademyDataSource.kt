package com.alcorp.projectacademyapp.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.alcorp.projectacademyapp.data.source.local.entity.CourseEntity
import com.alcorp.projectacademyapp.data.source.local.entity.CourseWithModule
import com.alcorp.projectacademyapp.data.source.local.entity.ModuleEntity
import com.alcorp.projectacademyapp.vo.Resource

interface AcademyDataSource {

    fun getAllCourses(): LiveData<Resource<PagedList<CourseEntity>>>

    fun getCourseWithModules(courseId: String): LiveData<Resource<CourseWithModule>>

    fun getAllModulesByCourse(courseId: String): LiveData<Resource<List<ModuleEntity>>>

    fun getContent(moduleId: String): LiveData<Resource<ModuleEntity>>

    fun getBookmarkedCourses(): LiveData<PagedList<CourseEntity>>

    fun setCourseBookmark(course: CourseEntity, state: Boolean)

    fun setReadModule(module: ModuleEntity)
}