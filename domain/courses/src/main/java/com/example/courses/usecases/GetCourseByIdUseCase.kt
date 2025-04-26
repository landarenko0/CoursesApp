package com.example.courses.usecases

import com.example.courses.entities.Course
import com.example.courses.repository.CourseRepository

class GetCourseByIdUseCase(private val repository: CourseRepository) {

    suspend operator fun invoke(courseId: Long): Result<Course?> = repository.getCourseById(courseId)
}