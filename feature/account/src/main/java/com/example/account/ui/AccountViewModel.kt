package com.example.account.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.auth.usecases.SetUserLoggedOutUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

internal class AccountViewModel(
    private val setUserLoggedOutUseCase: SetUserLoggedOutUseCase
) : ViewModel() {

    private val _event = Channel<AccountEvent>()
    val event = _event.receiveAsFlow()

    fun logOut() {
        setUserLoggedOutUseCase()
        viewModelScope.launch { _event.send(AccountEvent.NavigateToLoginScreen) }
    }
}