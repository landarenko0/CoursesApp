package com.example.liked_courses.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.liked_courses.R

@Composable
internal fun EmptyCoursesListScreen(
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        Text(
            text = stringResource(R.string.empty_liked_courses_list),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}