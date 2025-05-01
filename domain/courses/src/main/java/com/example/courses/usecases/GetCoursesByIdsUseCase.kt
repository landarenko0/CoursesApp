package com.example.courses.usecases

import com.example.courses.entities.ApiResult
import com.example.courses.entities.Course
import com.example.courses.repository.CourseRepository
import kotlinx.coroutines.flow.Flow

class GetCoursesByIdsUseCase(private val repository: CourseRepository) {

    operator fun invoke(courseIds: List<Long>): Flow<ApiResult<List<Course>>> =
        repository.getCoursesByIds(courseIds)
}