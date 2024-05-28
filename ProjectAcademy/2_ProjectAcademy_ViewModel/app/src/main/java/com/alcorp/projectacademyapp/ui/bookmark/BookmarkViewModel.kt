package com.alcorp.projectacademyapp.ui.bookmark

import androidx.lifecycle.ViewModel
import com.alcorp.projectacademyapp.data.CourseEntity
import com.alcorp.projectacademyapp.utils.DataDummy

class BookmarkViewModel : ViewModel() {
    fun getBookmarks(): List<CourseEntity> = DataDummy.generateDummyCourses()
}