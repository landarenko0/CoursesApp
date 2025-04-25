package com.example.domain.usecase

import com.example.domain.entities.Course
import com.example.domain.repository.CourseRepository

class GetAllCoursesUseCase(private val repository: CourseRepository) {

    suspend operator fun invoke(): List<Course> = repository.getAllCourses()
}