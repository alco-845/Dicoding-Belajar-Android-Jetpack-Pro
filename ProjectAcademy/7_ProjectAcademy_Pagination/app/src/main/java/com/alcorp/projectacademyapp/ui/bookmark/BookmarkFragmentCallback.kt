package com.alcorp.projectacademyapp.ui.bookmark

import com.alcorp.projectacademyapp.data.source.local.entity.CourseEntity

interface BookmarkFragmentCallback {
    fun onShareClick(course: CourseEntity)
}