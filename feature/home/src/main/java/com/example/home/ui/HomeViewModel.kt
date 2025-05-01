package com.example.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.entities.mappers.toItem
import com.example.courses.entities.ApiResult
import com.example.courses.entities.Course
import com.example.courses.usecases.GetAllCoursesUseCase
import com.example.liked_courses.usecases.GetLikedCoursesIdsUseCase
import com.example.liked_courses.usecases.MarkCourseAsLikedUseCase
import com.example.liked_courses.usecases.MarkCourseAsUnlikedUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.launchIn
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

    init {
        getLikedCoursesUseCase()
            .combine(getAllCoursesUseCase()) { likedCourses, allCoursesResult ->
                when (allCoursesResult) {
                    is ApiResult.Success -> {
                        _uiState.update {
                            HomeUiState.Success(
                                likedCoursesIds = likedCourses,
                                courses = withContext(Dispatchers.Default) {
                                    val courseItems = allCoursesResult.data.map { it.toItem() }

                                    when (currentOrderType) {
                                        OrderType.PUBLISH_DATE_ASC -> courseItems.sortedBy { it.publishDate }
                                        OrderType.PUBLISH_DATE_DESC -> courseItems.sortedByDescending { it.publishDate }
                                    }
                                }
                            )
                        }
                    }

                    is ApiResult.Failure -> _uiState.update { HomeUiState.Failure }

                    ApiResult.Loading -> _uiState.update { HomeUiState.Loading }
                }
            }
            .launchIn(viewModelScope)
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

    fun onCourseLikeClick(courseId: Long, hasLike: Boolean) {
        viewModelScope.launch {
            if (hasLike) markCourseAsUnlikedUseCase(courseId)
            else markCourseAsLikedUseCase(courseId)
        }
    }

    fun getCourses() {
        viewModelScope.launch {
            _uiState.update { HomeUiState.Loading }

            val allCoursesResult: ApiResult<List<Course>> = getAllCoursesUseCase().last()

            if (allCoursesResult.isSuccess) {
                val courses = (allCoursesResult as ApiResult.Success).data
                val orderedCourses = async(Dispatchers.Default) {
                    courses.map { it.toItem() }.sortedBy { it.publishDate }
                }.await()

                if (_uiState.value is HomeUiState.Success) {
                    _uiState.update { (it as HomeUiState.Success).copy(courses = orderedCourses) }
                } else {
                    _uiState.update { HomeUiState.Success(courses = orderedCourses) }
                }
            } else {
                _uiState.update { HomeUiState.Failure }
            }
        }
    }
}