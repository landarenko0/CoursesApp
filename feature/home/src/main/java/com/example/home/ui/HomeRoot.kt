package com.example.home.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.core.ui.components.CoursesList
import com.example.home.ui.components.ChangeOrderText
import com.example.home.ui.components.FilterCard
import com.example.home.ui.components.SearchTextField
import org.koin.androidx.compose.koinViewModel
import com.example.core.R as coreR
import com.example.home.R as homeR

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

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(coreR.dimen.medium)),
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(coreR.dimen.small)),
            modifier = Modifier.fillMaxWidth()
        ) {
            SearchTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier.weight(1f)
            )

            FilterCard(modifier = Modifier.size(dimensionResource(homeR.dimen.filter_card_size)))
        }

        ChangeOrderText(modifier = Modifier.align(Alignment.End))

        CoursesList(
            courses = uiState.courses,
            onCourseClick = {},
            onCourseLikeClick = {},
            contentPadding = PaddingValues(bottom = dimensionResource(coreR.dimen.medium)),
            modifier = Modifier.fillMaxSize()
        )
    }
}