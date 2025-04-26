package com.example.liked_courses.repository

import com.example.liked_courses.local.dao.LikedCoursesDao
import com.example.liked_courses.local.entities.LikedCourseDb
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

internal class LikedCoursesRepositoryImpl(
    private val likedCoursesDao: LikedCoursesDao,
    private val dispatcher: CoroutineDispatcher
) : LikedCoursesRepository {

    override fun getLikedCoursesIds(): Flow<List<Long>> = likedCoursesDao.getLikedCourses()
        .map { likedCourses -> likedCourses.map { it.courseId } }
        .flowOn(dispatcher)

    override suspend fun markCourseAsLiked(courseId: Long) = withContext(dispatcher) {
        likedCoursesDao.insertLikedCourse(LikedCourseDb(courseId))
    }

    override suspend fun markCourseAsUnliked(courseId: Long) = withContext(dispatcher) {
        likedCoursesDao.deleteLikedCourse(LikedCourseDb(courseId))
    }
}