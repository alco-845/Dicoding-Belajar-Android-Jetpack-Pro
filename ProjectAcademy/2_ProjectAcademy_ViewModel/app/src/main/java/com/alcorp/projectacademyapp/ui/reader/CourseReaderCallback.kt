package com.alcorp.projectacademyapp.ui.reader

interface CourseReaderCallback {
    fun moveTo(position: Int, moduleId: String)
}