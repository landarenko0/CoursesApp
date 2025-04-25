package com.example.domain.usecase

import com.example.domain.repository.LikedCoursesRepository
import kotlinx.coroutines.flow.Flow

class GetLikedCoursesIdsUseCase(private val repository: LikedCoursesRepository) {

    operator fun invoke(): Flow<List<Long>> = repository.getLikedCoursesIds()
}