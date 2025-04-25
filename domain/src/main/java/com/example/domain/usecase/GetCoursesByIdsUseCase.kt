package com.example.domain.usecase

import com.example.domain.entities.Course
import com.example.domain.repository.CourseRepository

class GetCoursesByIdsUseCase(private val repository: CourseRepository) {

    suspend operator fun invoke(courseIds: List<Long>): List<Course> =
        repository.getCoursesByIds(courseIds)
}