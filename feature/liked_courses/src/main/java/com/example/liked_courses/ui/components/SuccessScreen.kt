package com.example.liked_courses.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.core.domain.entities.CourseItem
import com.example.core.ui.components.CourseCard
import com.example.liked_courses.ui.LikedCoursesUiState
import com.example.core.R as coreR
import com.example.liked_courses.R as likedCoursesR

@Composable
internal fun SuccessScreen(
    uiState: LikedCoursesUiState.Success,
    onCourseLikeClick: (CourseItem) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(coreR.dimen.medium)),
        contentPadding = PaddingValues(bottom = dimensionResource(coreR.dimen.medium)),
        modifier = modifier
    ) {
        item {
            Text(
                text = stringResource(likedCoursesR.string.favorite),
                style = MaterialTheme.typography.titleLarge
            )
        }

        items(
            items = uiState.courses,
            key = { it.id }
        ) { courseItem ->
            CourseCard(
                course = courseItem,
                onClick = {},
                onLikeClick = { onCourseLikeClick(courseItem) },
                modifier = Modifier.fillMaxWidth().animateItem()
            )
        }
    }
}