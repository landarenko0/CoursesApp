package com.example.liked_courses.usecases

import com.example.liked_courses.repository.LikedCoursesRepository

class MarkCourseAsLikedUseCase(private val repository: LikedCoursesRepository) {

    suspend operator fun invoke(courseId: Long) = repository.markCourseAsLiked(courseId)
}