package com.alcorp.projectacademyapp.ui.bookmark

import com.alcorp.projectacademyapp.data.CourseEntity

interface BookmarkFragmentCallback {
    fun onShareClick(course: CourseEntity)
}