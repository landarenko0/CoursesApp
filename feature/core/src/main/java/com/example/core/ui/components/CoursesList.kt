package com.example.core.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.core.R
import com.example.core.domain.entities.CourseItem

@Composable
fun CoursesList(
    courses: List<CourseItem>,
    onCourseClick: () -> Unit,
    onCourseLikeClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues()
) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.medium)),
        contentPadding = contentPadding,
        modifier = modifier
    ) {
        items(
            items = courses,
            key = { it.id }
        ) { courseItem ->
            CourseCard(
                course = courseItem,
                onClick = onCourseClick,
                onLikeClick = onCourseLikeClick,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}