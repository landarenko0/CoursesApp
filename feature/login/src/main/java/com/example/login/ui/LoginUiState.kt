package com.example.login.ui

internal data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val showPassword: Boolean = false,
    val loginButtonEnabled: Boolean = false
)