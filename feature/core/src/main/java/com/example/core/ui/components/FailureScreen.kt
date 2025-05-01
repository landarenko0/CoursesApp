package com.example.core.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.core.R

@Composable
fun FailureScreen(
    retry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.medium)),
            modifier = Modifier.align(Alignment.Center)
        ) {
            Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = null
            )

            Text(
                text = stringResource(R.string.data_fetching_error),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium
            )

            CoursesAppPrimaryButton(
                onClick = retry,
                text = stringResource(R.string.retry)
            )
        }
    }
}