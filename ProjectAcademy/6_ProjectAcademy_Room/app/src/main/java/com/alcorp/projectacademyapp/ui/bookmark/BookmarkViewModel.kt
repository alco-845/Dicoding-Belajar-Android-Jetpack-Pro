package com.alcorp.projectacademyapp.ui.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.alcorp.projectacademyapp.data.source.local.entity.CourseEntity
import com.alcorp.projectacademyapp.data.AcademyRepository

class BookmarkViewModel(private val academyRepository: AcademyRepository) : ViewModel() {
    fun getBookmarks(): LiveData<List<CourseEntity>> = academyRepository.getBookmarkedCourses()
}