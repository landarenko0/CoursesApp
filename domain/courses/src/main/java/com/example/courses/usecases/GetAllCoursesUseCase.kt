package com.example.courses.usecases

import com.example.courses.entities.Course
import com.example.courses.repository.CourseRepository

class GetAllCoursesUseCase(private val repository: CourseRepository) {

    suspend operator fun invoke(): Result<List<Course>> = repository.getAllCourses()
}