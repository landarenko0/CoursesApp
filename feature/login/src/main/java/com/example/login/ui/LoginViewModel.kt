package com.example.login.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.auth.usecases.SetUserLoggedInUseCase
import com.example.auth.usecases.ValidateEmailAndPasswordUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class LoginViewModel(
    private val validateEmailAndPasswordUseCase: ValidateEmailAndPasswordUseCase,
    private val setUserLoggedInUseCase: SetUserLoggedInUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    private val _event = Channel<LoginEvent>()
    val event = _event.receiveAsFlow()

    fun updateEmail(email: String) {
        if (containsCyrillic(email)) return

        val password = _uiState.value.password
        val validateResult: Result<Nothing?> = validateEmailAndPasswordUseCase(email, password)

        _uiState.update {
            it.copy(
                email = email,
                loginButtonEnabled = validateResult.isSuccess
            )
        }
    }

    private fun containsCyrillic(text: String): Boolean {
        val cyrillicRegex = Regex("[\\u0400-\\u04FF]")
        return cyrillicRegex.containsMatchIn(text)
    }

    fun updatePassword(password: String) {
        val email = _uiState.value.email
        val validateResult: Result<Nothing?> = validateEmailAndPasswordUseCase(email, password)

        _uiState.update {
            it.copy(
                password = password,
                loginButtonEnabled = validateResult.isSuccess
            )
        }
    }

    fun changePasswordVisibility() {
        _uiState.update { it.copy(showPassword = !_uiState.value.showPassword) }
    }

    fun onLoginButtonClick() {
        setUserLoggedInUseCase()
        viewModelScope.launch { _event.send(LoginEvent.NavigateToHomeScreen) }
    }
}