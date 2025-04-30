package com.example.onboarding.domain.usecases

import com.example.onboarding.domain.repository.OnboardingRepository

class SetUserClosedOnboardingScreenUseCase internal constructor(
    private val repository: OnboardingRepository
) {

    operator fun invoke() = repository.setUserClosedOnboardingScreen()
}