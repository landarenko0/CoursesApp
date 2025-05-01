package com.example.liked_courses.ui

import com.example.core.domain.entities.CourseItem

internal sealed interface LikedCoursesUiState {

    data object Loading : LikedCoursesUiState

    data class Success(val courses: List<CourseItem> = emptyList()) : LikedCoursesUiState

    data object Failure : LikedCoursesUiState
}