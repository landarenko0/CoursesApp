package com.example.liked_courses.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.core.ui.components.FailureScreen
import com.example.core.ui.components.LoadingScreen
import com.example.liked_courses.ui.components.EmptyCoursesListScreen
import com.example.liked_courses.ui.components.SuccessScreen
import org.koin.androidx.compose.koinViewModel
import com.example.core.R as coreR

@Composable
fun LikedCoursesRoot() {
    LikedCoursesScreen(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = dimensionResource(coreR.dimen.medium),
                start = dimensionResource(coreR.dimen.medium),
                end = dimensionResource(coreR.dimen.medium)
            )
    )
}

@Composable
private fun LikedCoursesScreen(
    modifier: Modifier = Modifier,
    viewModel: LikedCoursesViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(coreR.dimen.medium)),
        modifier = modifier
    ) {
        when (uiState) {
            LikedCoursesUiState.Loading -> LoadingScreen(Modifier.fillMaxSize())

            LikedCoursesUiState.Failure -> FailureScreen(
                retry = viewModel::onRetryButtonClick,
                modifier = Modifier.fillMaxSize()
            )

            is LikedCoursesUiState.Success -> {
                if ((uiState as LikedCoursesUiState.Success).courses.isNotEmpty()) {
                    SuccessScreen(
                        uiState = uiState as LikedCoursesUiState.Success,
                        onCourseLikeClick = viewModel::onCourseLikedClick,
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    EmptyCoursesListScreen(Modifier.fillMaxSize())
                }
            }
        }
    }
}