package com.example.onboarding.ui

sealed interface OnboardingEvent {

    data object NavigateToLoginScreen : OnboardingEvent
}