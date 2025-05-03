package com.example.liked_courses.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.entities.CourseItem
import com.example.core.domain.entities.mappers.toItem
import com.example.courses.entities.Course
import com.example.courses.usecases.GetCoursesByIdsUseCase
import com.example.liked_courses.usecases.GetLikedCoursesIdsUseCase
import com.example.liked_courses.usecases.MarkCourseAsUnlikedUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class LikedCoursesViewModel(
    private val getCoursesByIdsUseCase: GetCoursesByIdsUseCase,
    getLikedCoursesUseCase: GetLikedCoursesIdsUseCase,
    private val markCourseAsUnlikedUseCase: MarkCourseAsUnlikedUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<LikedCoursesUiState> =
        MutableStateFlow(LikedCoursesUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private var likedCoursesIds: List<Long> = emptyList()

    init {
        getLikedCoursesUseCase()
            .onEach { likedCourses ->
                likedCoursesIds = likedCourses.toList()

                if (likedCourses.isNotEmpty()) {
                    updateStateByResult(getCoursesByIdsUseCase(likedCourses))
                } else {
                    _uiState.update { LikedCoursesUiState.Success() }
                }
            }
            .launchIn(viewModelScope)
    }

    fun onCourseLikedClick(courseItem: CourseItem) {
        viewModelScope.launch { markCourseAsUnlikedUseCase(courseItem.id) }
    }

    fun onRetryButtonClick() {
        viewModelScope.launch {
            _uiState.update { LikedCoursesUiState.Loading }

            updateStateByResult(getCoursesByIdsUseCase(likedCoursesIds))
        }
    }

    private fun updateStateByResult(result: Result<List<Course>>) {
        _uiState.update {
            if (result.isSuccess) {
                LikedCoursesUiState.Success(
                    courses = result.getOrDefault(emptyList()).map {
                        it.toItem().copy(hasLike = true)
                    }
                )
            } else {
                LikedCoursesUiState.Failure
            }
        }
    }
}