package com.alcorp.projectacademyapp.data.source.remote

import android.os.Handler
import com.alcorp.projectacademyapp.data.source.remote.response.ContentResponse
import com.alcorp.projectacademyapp.data.source.remote.response.CourseResponse
import com.alcorp.projectacademyapp.data.source.remote.response.ModuleResponse
import com.alcorp.projectacademyapp.utils.EspressoIdlingResource
import com.alcorp.projectacademyapp.utils.JsonHelper

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {

    private val handler = Handler()

    companion object {
        private const val SERVICE_LATENCY_IN_MILIS: Long = 2000

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(helper)
            }
    }

    fun getAllCourses(callback: LoadCourseCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            callback.onAllCoursesReceived(jsonHelper.loadCourses())
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILIS)
    }

    fun getModules(courseId: String, callback: LoadModulesCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            callback.onAllModulesReceived(jsonHelper.loadModule(courseId))
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILIS)
    }

    fun getContent(moduleId: String, callback: LoadContentCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            callback.onContentReceived(jsonHelper.loadContent(moduleId))
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILIS)
    }

    interface LoadCourseCallback {
        fun onAllCoursesReceived(courseResponse: List<CourseResponse>)
    }

    interface LoadModulesCallback {
        fun onAllModulesReceived(moduleResponse: List<ModuleResponse>)
    }

    interface LoadContentCallback {
        fun onContentReceived(contentResponse: ContentResponse)
    }
}
