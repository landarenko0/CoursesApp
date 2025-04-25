package com.example.domain.usecase

import com.example.domain.repository.LikedCoursesRepository

class MarkCourseAsUnlikedUseCase(private val repository: LikedCoursesRepository) {

    suspend operator fun invoke(courseId: Long) = repository.markCourseAsUnliked(courseId)
}