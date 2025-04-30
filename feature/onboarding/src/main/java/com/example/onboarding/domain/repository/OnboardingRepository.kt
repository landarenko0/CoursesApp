package com.example.onboarding.domain.repository

internal interface OnboardingRepository {

    fun checkUserClosedOnboardingScreen(): Boolean

    fun setUserClosedOnboardingScreen()
}