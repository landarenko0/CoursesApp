package com.example.login.ui

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val loginButtonEnabled: Boolean = false
)