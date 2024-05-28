package com.alcorp.projectacademyapp.data.source

import com.alcorp.projectacademyapp.data.ContentEntity
import com.alcorp.projectacademyapp.data.CourseEntity
import com.alcorp.projectacademyapp.data.ModuleEntity
import com.alcorp.projectacademyapp.data.source.remote.RemoteDataSource

class AcademyRepository private constructor(private val remoteDataSource: RemoteDataSource) : AcademyDataSource {

    companion object {
        @Volatile
        private var instance: AcademyRepository? = null

        fun getInstance(remoteData: RemoteDataSource) : AcademyRepository =
            instance ?: synchronized(this) {
                instance ?: AcademyRepository(remoteData)
            }
    }

    override fun getAllCourses(): ArrayList<CourseEntity> {
        val courseResponse = remoteDataSource.getAllCourses()
        val courseList = ArrayList<CourseEntity>()
        for (response in courseResponse) {
            val course = CourseEntity(
                response.id,
                response.title,
                response.description,
                response.date,
                false,
                response.imagePath
            )
            courseList.add(course)
        }
        return courseList
    }

    override fun getBookmarkedCourses(): ArrayList<CourseEntity> {
        val courseResponse = remoteDataSource.getAllCourses()
        val courseList = ArrayList<CourseEntity>()
        for (response in courseResponse) {
            val course = CourseEntity(
                response.id,
                response.title,
                response.description,
                response.date,
                false,
                response.imagePath
            )
            courseList.add(course)
        }
        return courseList
    }

    override fun getCourseWithModules(courseId: String): CourseEntity {
        val courseResponse = remoteDataSource.getAllCourses()
        lateinit var course: CourseEntity
        for(response in courseResponse) {
            if (response.id == courseId) {
                course = CourseEntity(
                    response.id,
                    response.title,
                    response.description,
                    response.date,
                    false,
                    response.imagePath
                )
            }
        }
        return course
    }

    override fun getAllModulesByCourse(courseId: String): ArrayList<ModuleEntity> {
        val modulesResponses = remoteDataSource.getModules(courseId)
        val moduleList = ArrayList<ModuleEntity>()
        for (response in modulesResponses) {
            val course = ModuleEntity(
                response.moduleId,
                response.courseId,
                response.title,
                response.position,
                false
            )
            moduleList.add(course)
        }
        return moduleList
    }

    override fun getContent(courseId: String, moduleId: String): ModuleEntity {
        val moduleResponses = remoteDataSource.getModules(courseId)
        lateinit var module: ModuleEntity
        for (response in moduleResponses) {
            if (response.moduleId == moduleId) {
                module = ModuleEntity(
                    response.moduleId,
                    response.courseId,
                    response.title,
                    response.position,
                    false
                )
                module.contentEntity = ContentEntity(remoteDataSource.getContent(moduleId).content)
                break
            }
        }
        return module
    }

}