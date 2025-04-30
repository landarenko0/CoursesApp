package com.example.onboarding.domain.usecases

import com.example.onboarding.domain.repository.OnboardingRepository

class CheckUserClosedOnboardingScreenUseCase internal constructor(
    private val repository: OnboardingRepository
) {

    operator fun invoke(): Boolean = repository.checkUserClosedOnboardingScreen()
}