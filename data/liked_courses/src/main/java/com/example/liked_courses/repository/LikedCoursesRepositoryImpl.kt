package com.example.liked_courses.repository

import com.example.liked_courses.local.dao.LikedCoursesDao
import com.example.liked_courses.local.entities.LikedCourseDb
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class LikedCoursesRepositoryImpl(
    private val likedCoursesDao: LikedCoursesDao
) : LikedCoursesRepository {

    override fun getLikedCoursesIds(): Flow<List<Long>> = likedCoursesDao.getLikedCourses()
        .map { likedCourses -> likedCourses.map { it.courseId } }

    override suspend fun markCourseAsLiked(courseId: Long) {
        return likedCoursesDao.insertLikedCourse(LikedCourseDb(courseId))
    }

    override suspend fun markCourseAsUnliked(courseId: Long) {
        likedCoursesDao.deleteLikedCourse(LikedCourseDb(courseId))
    }
}