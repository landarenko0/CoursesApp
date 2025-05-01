package com.example.courses.repository

import com.example.courses.entities.ApiResult
import com.example.courses.entities.Course
import kotlinx.coroutines.flow.Flow

interface CourseRepository {

    fun getAllCourses(): Flow<ApiResult<List<Course>>>

    fun getCoursesByIds(courseIds: List<Long>): Flow<ApiResult<List<Course>>>
}