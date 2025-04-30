package com.example.login.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.core.ui.components.CoursesAppPrimaryButton
import com.example.core.ui.components.CoursesAppTextField
import com.example.core.ui.components.OkButton
import com.example.core.ui.components.VkButton
import com.example.core.utils.OK_URL
import com.example.core.utils.VK_URL
import org.koin.androidx.compose.koinViewModel
import com.example.core.R as coreR
import com.example.login.R as loginR

@Composable
fun LoginRoot(
    onLoginButtonClick: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        LoginScreen(
            onLoginButtonClick = onLoginButtonClick,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(dimensionResource(coreR.dimen.medium))
        )
    }
}

@Composable
private fun LoginScreen(
    onLoginButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = koinViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            when (event) {
                LoginEvent.NavigateToHomeScreen -> onLoginButtonClick()
            }
        }
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val uriHandler = LocalUriHandler.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .clickable(
                onClick = {
                    keyboardController?.hide()
                    focusManager.clearFocus()
                },
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            )
    ) {
        Spacer(Modifier.weight(1f))

        Text(
            text = stringResource(loginR.string.login),
            style = MaterialTheme.typography.headlineLarge,
        )

        Spacer(Modifier.height(dimensionResource(coreR.dimen.extra_large)))

        Column(
            verticalArrangement = Arrangement.spacedBy(dimensionResource(coreR.dimen.large))
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(dimensionResource(coreR.dimen.small))
            ) {
                Text(
                    text = stringResource(loginR.string.email),
                    style = MaterialTheme.typography.titleMedium
                )

                CoursesAppTextField(
                    value = uiState.email,
                    onValueChange = viewModel::updateEmail,
                    placeholder = stringResource(loginR.string.email_hint),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(dimensionResource(coreR.dimen.small))
            ) {
                Text(
                    text = stringResource(loginR.string.password),
                    style = MaterialTheme.typography.titleMedium
                )

                CoursesAppTextField(
                    value = uiState.password,
                    onValueChange = viewModel::updatePassword,
                    placeholder = stringResource(loginR.string.password_hint),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    visualTransformation = if (uiState.showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                    singleLine = true,
                    trailingIcon = {
                        val iconResId = if (uiState.showPassword) {
                            coreR.drawable.visibility_off_icon
                        } else {
                            coreR.drawable.visibility_icon
                        }

                        val descriptionResId = if (uiState.showPassword) {
                            coreR.string.hide_password
                        } else {
                            coreR.string.show_password
                        }

                        IconButton(
                            onClick = viewModel::changePasswordVisibility,
                            modifier = Modifier.padding(end = dimensionResource(coreR.dimen.small))
                        ) {
                            Icon(
                                painter = painterResource(iconResId),
                                contentDescription = stringResource(descriptionResId)
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            CoursesAppPrimaryButton(
                onClick = viewModel::onLoginButtonClick,
                text = stringResource(loginR.string.login),
                enabled = uiState.loginButtonEnabled,
                modifier = Modifier.fillMaxWidth()
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(dimensionResource(coreR.dimen.small)),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(dimensionResource(coreR.dimen.small)),
                ) {
                    Text(
                        text = stringResource(loginR.string.no_account),
                        style = MaterialTheme.typography.bodySmall
                    )

                    Text(
                        text = stringResource(loginR.string.registration),
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                Text(
                    text = stringResource(loginR.string.forgot_password),
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        Spacer(Modifier.height(dimensionResource(coreR.dimen.extra_large)))

        HorizontalDivider(Modifier.fillMaxWidth())

        Spacer(Modifier.height(dimensionResource(coreR.dimen.extra_large)))

        Row(
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(coreR.dimen.medium), Alignment.CenterHorizontally),
            modifier = Modifier.fillMaxSize()
        ) {
            VkButton(
                onClick = { uriHandler.openUri(VK_URL) },
                modifier = Modifier.weight(1f)
            )

            OkButton(
                onClick = { uriHandler.openUri(OK_URL) },
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(Modifier.weight(1f))
    }
}