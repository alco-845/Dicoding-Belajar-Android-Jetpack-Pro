package com.alcorp.projectacademyapp.data.source

import com.alcorp.projectacademyapp.data.CourseEntity
import com.alcorp.projectacademyapp.data.ModuleEntity

interface AcademyDataSource {

    fun getAllCourses() : List<CourseEntity>

    fun getBookmarkedCourses() : List<CourseEntity>

    fun getCourseWithModules(courseId: String) : CourseEntity

    fun getAllModulesByCourse(courseId: String) : List<ModuleEntity>

    fun getContent(courseId: String, moduleId: String) : ModuleEntity
}