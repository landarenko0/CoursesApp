package com.example.domain.usecase

import com.example.domain.entities.Course
import com.example.domain.repository.CourseRepository

class GetCourseByIdUseCase(private val repository: CourseRepository) {

    suspend operator fun invoke(courseId: Long): Course? = repository.getCourseById(courseId)
}