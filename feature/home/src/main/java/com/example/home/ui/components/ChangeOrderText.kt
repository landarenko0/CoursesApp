package com.example.home.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.core.R as coreR
import com.example.home.R as homeR

@Composable
internal fun ChangeOrderText(modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(coreR.dimen.small)),
        modifier = modifier
    ) {
        Text(
            text = stringResource(homeR.string.order_type),
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.primary
        )

        Icon(
            painter = painterResource(coreR.drawable.change_order_icon),
            tint = MaterialTheme.colorScheme.primary,
            contentDescription = null
        )
    }
}