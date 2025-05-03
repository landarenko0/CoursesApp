package com.example.courses.repository

import com.example.courses.entities.Course

interface CourseRepository {

    suspend fun getAllCourses(): Result<List<Course>>

    suspend fun getCoursesByIds(courseIds: List<Long>): Result<List<Course>>
}