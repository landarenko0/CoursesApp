package com.example.login.ui

sealed interface LoginEvent {

    data object NavigateToHomeScreen : LoginEvent
}