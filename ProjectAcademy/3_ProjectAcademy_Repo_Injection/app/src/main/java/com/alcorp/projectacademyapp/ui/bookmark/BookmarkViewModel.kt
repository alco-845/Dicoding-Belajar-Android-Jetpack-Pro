package com.alcorp.projectacademyapp.ui.bookmark

import androidx.lifecycle.ViewModel
import com.alcorp.projectacademyapp.data.CourseEntity
import com.alcorp.projectacademyapp.data.source.AcademyRepository

class BookmarkViewModel(private val academyRepository: AcademyRepository) : ViewModel() {
    fun getBookmarks(): List<CourseEntity> = academyRepository.getBookmarkedCourses()
}