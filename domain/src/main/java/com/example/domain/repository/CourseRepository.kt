package com.example.domain.repository

import com.example.domain.entities.Course

interface CourseRepository {

    suspend fun getAllCourses(): List<Course>

    suspend fun getCoursesByIds(courseIds: List<Long>): List<Course>

    suspend fun getCourseById(courseId: Long): Course?
}