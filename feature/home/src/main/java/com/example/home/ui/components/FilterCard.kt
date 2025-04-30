package com.example.home.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.ui.theme.CoursesAppTheme
import com.example.core.ui.theme.DarkGrey
import com.example.core.R as coreR
import com.example.home.R as homeR

@Composable
internal fun FilterCard(modifier: Modifier = Modifier) {
    Card(
        shape = CircleShape,
        colors = CardDefaults.cardColors(containerColor = DarkGrey),
        modifier = modifier
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Icon(
                painter = painterResource(coreR.drawable.filter_icon),
                contentDescription = stringResource(homeR.string.filter_content_description),
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Preview
@Composable
private fun FilterCardPreview() {
    CoursesAppTheme {
        FilterCard(modifier = Modifier.size(56.dp))
    }
}