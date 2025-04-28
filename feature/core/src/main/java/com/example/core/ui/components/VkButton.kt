package com.example.core.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.core.R
import com.example.core.ui.theme.VkColor

@Composable
fun VkButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(containerColor = VkColor),
        modifier = modifier.height(dimensionResource(R.dimen.button_height))
    ) {
        Icon(
            painter = painterResource(R.drawable.vk_icon),
            contentDescription = stringResource(R.string.vk_btn_content_description),
        )
    }
}