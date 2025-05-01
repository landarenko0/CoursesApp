package com.example.account.ui

internal sealed interface AccountEvent {

    data object NavigateToLoginScreen : AccountEvent
}