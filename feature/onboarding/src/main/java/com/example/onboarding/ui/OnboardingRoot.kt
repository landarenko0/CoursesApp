package com.example.onboarding.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.core.ui.components.CoursesAppPrimaryButton
import com.example.onboarding.ui.components.CoursesRows
import org.koin.androidx.compose.koinViewModel
import com.example.core.R as coreR
import com.example.onboarding.R as onboardingR

@Composable
fun OnboardingRoot(
    onContinueButtonClick: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        OnboardingScreen(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(vertical = dimensionResource(coreR.dimen.large)),
            onContinueButtonClick = onContinueButtonClick
        )
    }
}

@Composable
private fun OnboardingScreen(
    onContinueButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: OnboardingViewModel = koinViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            when (event) {
                OnboardingEvent.NavigateToLoginScreen -> onContinueButtonClick()
            }
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(coreR.dimen.extra_large)),
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        Spacer(Modifier.weight(1f))

        Text(
            text = stringResource(onboardingR.string.onboarding_title),
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = dimensionResource(coreR.dimen.extra_large))
        )

        CoursesRows(modifier = Modifier.fillMaxWidth())

        Spacer(Modifier.weight(1f))

        CoursesAppPrimaryButton(
            onClick = viewModel::onLoginButtonClick,
            text = stringResource(onboardingR.string.continue_btn),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(coreR.dimen.medium))
        )
    }
}