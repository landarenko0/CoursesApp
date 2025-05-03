package com.example.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.entities.CourseItem
import com.example.core.domain.entities.mappers.toItem
import com.example.courses.entities.Course
import com.example.courses.usecases.GetAllCoursesUseCase
import com.example.liked_courses.usecases.GetLikedCoursesIdsUseCase
import com.example.liked_courses.usecases.MarkCourseAsLikedUseCase
import com.example.liked_courses.usecases.MarkCourseAsUnlikedUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal class HomeViewModel(
    private val getAllCoursesUseCase: GetAllCoursesUseCase,
    getLikedCoursesUseCase: GetLikedCoursesIdsUseCase,
    private val markCourseAsLikedUseCase: MarkCourseAsLikedUseCase,
    private val markCourseAsUnlikedUseCase: MarkCourseAsUnlikedUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private var currentOrderType = OrderType.PUBLISH_DATE_ASC

    private var likedCoursesIds: List<Long> = emptyList()

    init {
        getLikedCoursesUseCase()
            .onEach { likedCourses ->
                likedCoursesIds = likedCourses
                updateStateByResult(getAllCoursesUseCase())
            }.launchIn(viewModelScope)
    }

    fun changeCoursesOrderType() {
        val state = _uiState.value as? HomeUiState.Success

        if (state != null) {
            viewModelScope.launch(Dispatchers.Default) {
                val courses = state.courses

                val reorderedCourses = when (currentOrderType) {
                    OrderType.PUBLISH_DATE_ASC -> {
                        currentOrderType = OrderType.PUBLISH_DATE_DESC
                        courses.sortedByDescending { it.publishDate }
                    }

                    OrderType.PUBLISH_DATE_DESC -> {
                        currentOrderType = OrderType.PUBLISH_DATE_ASC
                        courses.sortedBy { it.publishDate }
                    }
                }

                _uiState.update { state.copy(courses = reorderedCourses) }
            }
        }
    }

    fun onCourseLikeClick(courseItem: CourseItem) {
        viewModelScope.launch {
            if (courseItem.hasLike) markCourseAsUnlikedUseCase(courseItem.id)
            else markCourseAsLikedUseCase(courseItem.id)
        }
    }

    fun onRetryButtonClick() {
        viewModelScope.launch {
            _uiState.update { HomeUiState.Loading }
            updateStateByResult(getAllCoursesUseCase())
        }
    }

    private suspend fun updateStateByResult(result: Result<List<Course>>) {
        _uiState.update {
            if (result.isSuccess) {
                HomeUiState.Success(
                    courses = withContext(Dispatchers.Default) {
                        val courseItems = result.getOrDefault(emptyList())
                            .map { it.toItem().copy(hasLike = it.id in likedCoursesIds) }

                        when (currentOrderType) {
                            OrderType.PUBLISH_DATE_ASC -> courseItems.sortedBy { it.publishDate }
                            OrderType.PUBLISH_DATE_DESC -> courseItems.sortedByDescending { it.publishDate }
                        }
                    }
                )
            } else {
                HomeUiState.Failure
            }
        }
    }
}