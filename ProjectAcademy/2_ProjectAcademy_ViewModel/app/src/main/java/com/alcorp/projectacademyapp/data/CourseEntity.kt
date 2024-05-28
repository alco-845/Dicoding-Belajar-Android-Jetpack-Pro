package com.alcorp.projectacademyapp.data

data class CourseEntity (
    var courseId: String,
    var title: String,
    var description: String,
    var deadline: String,
    var bookmarked: Boolean,
    var imagePath: String
)