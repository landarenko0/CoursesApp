package com.example.home.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.core.ui.theme.CoursesAppTheme
import com.example.core.ui.theme.DarkGrey
import com.example.home.R

@Composable
internal fun SearchTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        shape = MaterialTheme.shapes.medium,
        placeholder = {
            Text(
                text = stringResource(R.string.search_hint),
                modifier = Modifier.alpha(0.5f)
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = DarkGrey,
            unfocusedContainerColor = DarkGrey,
            unfocusedBorderColor = DarkGrey
        ),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.size(dimensionResource(R.dimen.search_icon_size))
            )
        },
        singleLine = true,
        modifier = modifier.height(dimensionResource(R.dimen.search_text_field_height))
    )
}

@Preview
@Composable
private fun SearchTextFieldPreview() {
    CoursesAppTheme {
        SearchTextField(
            value = "",
            onValueChange = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}