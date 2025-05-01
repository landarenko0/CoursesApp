package com.example.account.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.account.ui.components.OptionsCard
import org.koin.androidx.compose.koinViewModel
import com.example.core.R as coreR
import com.example.account.R as accountR

@Composable
fun AccountRoot(onLogOutButtonClick: () -> Unit) {
    AccountScreen(
        onLogOutButtonClick = onLogOutButtonClick,
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = dimensionResource(coreR.dimen.medium),
                start = dimensionResource(coreR.dimen.medium),
                end = dimensionResource(coreR.dimen.medium)
            )
    )
}

@Composable
private fun AccountScreen(
    onLogOutButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AccountViewModel = koinViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            when (event) {
                AccountEvent.NavigateToLoginScreen -> onLogOutButtonClick()
            }
        }
    }

    Column(modifier = modifier) {
        Text(
            text = stringResource(accountR.string.profile),
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(Modifier.height(dimensionResource(coreR.dimen.medium)))

        OptionsCard(
            onLogOutOptionClick = viewModel::logOut,
            modifier = Modifier.fillMaxWidth()
        )
    }
}