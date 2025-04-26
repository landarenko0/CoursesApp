package com.example.liked_courses.usecases

import com.example.liked_courses.repository.LikedCoursesRepository
import kotlinx.coroutines.flow.Flow

class GetLikedCoursesIdsUseCase(private val repository: LikedCoursesRepository) {

    operator fun invoke(): Flow<List<Long>> = repository.getLikedCoursesIds()
}