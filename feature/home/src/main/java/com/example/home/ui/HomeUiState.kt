package com.example.home.ui

import com.example.core.domain.entities.CourseItem

internal data class HomeUiState(val courses: List<CourseItem> = emptyList())