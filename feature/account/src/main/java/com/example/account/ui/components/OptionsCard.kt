package com.example.account.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.core.ui.theme.CoursesAppTheme
import com.example.core.ui.theme.DarkGrey
import com.example.account.R as accountR
import com.example.core.R as coreR

@Composable
internal fun OptionsCard(
    onLogOutOptionClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.cardColors(
            containerColor = DarkGrey,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(dimensionResource(coreR.dimen.small)),
            modifier = Modifier
                .padding(
                    vertical = dimensionResource(coreR.dimen.small),
                    horizontal = dimensionResource(coreR.dimen.medium)
                )
        ) {
            OptionRow(
                optionTitle = stringResource(accountR.string.message_to_support),
                onClick = {},
                modifier = Modifier.fillMaxWidth()
            )

            HorizontalDivider(Modifier.fillMaxWidth())

            OptionRow(
                optionTitle = stringResource(accountR.string.settings),
                onClick = {},
                modifier = Modifier.fillMaxWidth()
            )

            HorizontalDivider(Modifier.fillMaxWidth())

            OptionRow(
                optionTitle = stringResource(accountR.string.log_out),
                onClick = onLogOutOptionClick,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun OptionRow(
    optionTitle: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.clickable(
            onClick = onClick,
            indication = null,
            interactionSource = remember { MutableInteractionSource() }
        )
    ) {
        Text(
            text = optionTitle,
            style = MaterialTheme.typography.titleSmall
        )

        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null
        )
    }
}

@Preview
@Composable
private fun OptionsCardPreview() {
    CoursesAppTheme {
        OptionsCard({})
    }
}