package com.example.courses.usecases

import com.example.courses.entities.Course
import com.example.courses.repository.CourseRepository

class GetCoursesByIdsUseCase(private val repository: CourseRepository) {

    suspend operator fun invoke(courseIds: List<Long>): Result<List<Course>> =
        repository.getCoursesByIds(courseIds)
}