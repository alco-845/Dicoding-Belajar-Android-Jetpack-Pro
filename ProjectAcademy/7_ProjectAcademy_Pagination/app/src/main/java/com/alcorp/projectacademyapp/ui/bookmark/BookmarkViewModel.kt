package com.alcorp.projectacademyapp.ui.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.alcorp.projectacademyapp.data.AcademyRepository
import com.alcorp.projectacademyapp.data.source.local.entity.CourseEntity

class BookmarkViewModel(private val academyRepository: AcademyRepository) : ViewModel() {
    fun getBookmarks(): LiveData<PagedList<CourseEntity>> = academyRepository.getBookmarkedCourses()

    fun setBookmark(courseEntity: CourseEntity) {
        val newState = !courseEntity.bookmarked
        academyRepository.setCourseBookmark(courseEntity, newState)
    }
}