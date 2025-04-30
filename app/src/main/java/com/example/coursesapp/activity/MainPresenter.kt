package com.example.coursesapp.activity

import com.example.auth.usecases.CheckUserLoggedInUseCase
import com.example.coursesapp.navigation.AppScreens

internal class MainPresenter(
    private val checkUserLoggedInUseCase: CheckUserLoggedInUseCase
) {

    fun getStartScreen(): AppScreens {
        return if (!checkUserLoggedInUseCase()) {
            AppScreens.OnboardingScreen
        } else {
            AppScreens.HomeScreen
        }
    }
}