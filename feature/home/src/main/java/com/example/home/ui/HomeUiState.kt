package com.example.home.ui

import com.example.core.domain.entities.CourseItem

internal sealed interface HomeUiState {

    data object Loading : HomeUiState

    data class Success(
        val likedCoursesIds: List<Long> = emptyList(),
        val courses: List<CourseItem> = emptyList()
    ) : HomeUiState

    data object Failure : HomeUiState
}