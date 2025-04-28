package com.example.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.core.R
import com.example.core.ui.theme.OkGradientColor1
import com.example.core.ui.theme.OkGradientColor2

@Composable
fun OkButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val gradientColor = Brush.verticalGradient(listOf(OkGradientColor1, OkGradientColor2))

    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        modifier = modifier
            .clip(MaterialTheme.shapes.medium)
            .background(gradientColor)
            .height(dimensionResource(R.dimen.button_height))
    ) {
        Icon(
            painter = painterResource(R.drawable.ok_icon),
            contentDescription = stringResource(R.string.ok_btn_content_description),
        )
    }
}