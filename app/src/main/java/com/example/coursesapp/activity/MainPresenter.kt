package com.example.coursesapp.activity

import com.example.auth.usecases.CheckUserLoggedInUseCase
import com.example.coursesapp.navigation.AppScreens
import com.example.onboarding.domain.usecases.CheckUserClosedOnboardingScreenUseCase

internal class MainPresenter(
    private val checkUserClosedOnboardingScreenUseCase: CheckUserClosedOnboardingScreenUseCase,
    private val checkUserLoggedInUseCase: CheckUserLoggedInUseCase
) {

    fun getStartScreen(): AppScreens {
        return if (!checkUserClosedOnboardingScreenUseCase()) {
            AppScreens.OnboardingScreen
        } else {
            if (!checkUserLoggedInUseCase()) {
                AppScreens.LoginScreen
            } else {
                AppScreens.HomeScreen
            }
        }
    }
}