package com.example.core.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.core.R
import com.example.core.domain.entities.CourseItem

@Composable
fun CoursesList(
    likedCourses: List<Long>,
    courses: List<CourseItem>,
    onCourseClick: (CourseItem) -> Unit,
    onCourseLikeClick: (Long, Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.medium)),
        modifier = modifier
    ) {
        courses.forEach { courseItem ->
            CourseCard(
                course = courseItem,
                hasLike = courseItem.id in likedCourses,
                onClick = { onCourseClick(courseItem) },
                onLikeClick = { onCourseLikeClick(courseItem.id, courseItem.id in likedCourses) },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}