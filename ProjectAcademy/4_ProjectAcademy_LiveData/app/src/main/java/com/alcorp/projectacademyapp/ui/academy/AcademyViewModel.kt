package com.alcorp.projectacademyapp.ui.academy

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.alcorp.projectacademyapp.data.CourseEntity
import com.alcorp.projectacademyapp.data.source.AcademyRepository

class AcademyViewModel(private val academyRepository: AcademyRepository) : ViewModel() {
    fun getCourses(): LiveData<List<CourseEntity>> = academyRepository.getAllCourses()
}