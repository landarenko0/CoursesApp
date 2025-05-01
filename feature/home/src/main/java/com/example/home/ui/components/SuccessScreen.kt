package com.example.home.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.core.ui.components.CoursesList
import com.example.home.ui.HomeUiState
import com.example.core.R as coreR
import com.example.home.R as homeR

@Composable
internal fun SuccessScreen(
    uiState: HomeUiState.Success,
    changeCoursesOrderType: () -> Unit,
    onCourseLikeClick: (Long, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(coreR.dimen.medium)),
        contentPadding = PaddingValues(bottom = dimensionResource(coreR.dimen.medium)),
        modifier = modifier
    ) {
        item {
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
        }

        item {
            Column(modifier = Modifier.fillMaxWidth()) {
                ChangeOrderText(
                    onClick = changeCoursesOrderType,
                    modifier = Modifier.align(Alignment.End)
                )
            }
        }

        item {
            CoursesList(
                likedCourses = uiState.likedCoursesIds,
                courses = uiState.courses,
                onCourseClick = {},
                onCourseLikeClick = onCourseLikeClick,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}