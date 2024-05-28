package com.alcorp.projectacademyapp.ui.academy

import androidx.lifecycle.ViewModel
import com.alcorp.projectacademyapp.data.CourseEntity
import com.alcorp.projectacademyapp.data.source.AcademyRepository

class AcademyViewModel(private val academyRepository: AcademyRepository) : ViewModel() {
    fun getCourses(): List<CourseEntity> = academyRepository.getAllCourses()
}