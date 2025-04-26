package com.example.liked_courses.repository

import kotlinx.coroutines.flow.Flow

interface LikedCoursesRepository {

    fun getLikedCoursesIds(): Flow<List<Long>>

    suspend fun markCourseAsLiked(courseId: Long)

    suspend fun markCourseAsUnliked(courseId: Long)
}