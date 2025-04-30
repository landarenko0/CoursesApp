package com.example.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.entities.mappers.toItem
import com.example.courses.entities.Course
import com.example.courses.usecases.GetAllCoursesUseCase
import com.example.liked_courses.usecases.GetLikedCoursesIdsUseCase
import com.example.liked_courses.usecases.MarkCourseAsLikedUseCase
import com.example.liked_courses.usecases.MarkCourseAsUnlikedUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class HomeViewModel(
    private val getAllCoursesUseCase: GetAllCoursesUseCase,
    private val getLikedCoursesUseCase: GetLikedCoursesIdsUseCase,
    private val markCourseAsLikedUseCase: MarkCourseAsLikedUseCase,
    private val markCourseAsUnlikedUseCase: MarkCourseAsUnlikedUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
//        val likedCoursesFlow: Flow<List<Long>> = getLikedCoursesUseCase()
//        likedCoursesFlow
//            .onEach {}
//            .launchIn(viewModelScope)

        viewModelScope.launch {
            val allCoursesResult: Result<List<Course>> = getAllCoursesUseCase()

            if (allCoursesResult.isSuccess) {
                val courses = allCoursesResult.getOrDefault(emptyList())
                _uiState.update { it.copy(courses = courses.map { it.toItem() }) }
            }
        }
    }
}