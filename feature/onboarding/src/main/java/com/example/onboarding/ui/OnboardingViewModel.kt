package com.example.onboarding.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onboarding.domain.usecases.SetUserClosedOnboardingScreenUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

internal class OnboardingViewModel(
    private val setUserClosedOnboardingScreenUseCase: SetUserClosedOnboardingScreenUseCase
): ViewModel() {

    private val _event = Channel<OnboardingEvent>()
    val event = _event.receiveAsFlow()

    fun onLoginButtonClick() {
        setUserClosedOnboardingScreenUseCase()
        viewModelScope.launch { _event.send(OnboardingEvent.NavigateToLoginScreen) }
    }
}