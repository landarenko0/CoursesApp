package com.example.home.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.core.ui.components.FailureScreen
import com.example.core.ui.components.LoadingScreen
import com.example.home.ui.components.SuccessScreen
import org.koin.androidx.compose.koinViewModel
import com.example.core.R as coreR

@Composable
fun HomeRoot() {
    HomeScreen(
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
private fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (uiState) {
        HomeUiState.Loading -> LoadingScreen(modifier)

        HomeUiState.Failure -> FailureScreen(
            retry = viewModel::getCourses,
            modifier = modifier
        )

        is HomeUiState.Success -> {
            SuccessScreen(
                uiState = uiState as HomeUiState.Success,
                changeCoursesOrderType = viewModel::changeCoursesOrderType,
                onCourseLikeClick = viewModel::onCourseLikeClick,
                modifier = modifier
            )
        }
    }
}