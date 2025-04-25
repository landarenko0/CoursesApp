package com.example.domain.usecase

import com.example.domain.repository.LikedCoursesRepository

class MarkCourseAsLikedUseCase(private val repository: LikedCoursesRepository) {

    suspend operator fun invoke(courseId: Long) = repository.markCourseAsLiked(courseId)
}