package com.example.coursesapp.navigation

import kotlinx.serialization.Serializable

enum class Screen {
    ONBOARDING,
    LOGIN,
    HOME,
    LIKED_COURSES,
    ACCOUNT;

    companion object {

        fun getScreenByRoute(route: String?): Screen? {
            return when {
                route == null -> null
                route.contains("OnboardingScreen") -> ONBOARDING
                route.contains("LoginScreen") -> LOGIN
                route.contains("HomeScreen") -> HOME
                route.contains("LikedCoursesScreen") -> LIKED_COURSES
                route.contains("AccountScreen") -> ACCOUNT
                else -> throw IllegalArgumentException("Unknown screen route")
            }
        }
    }
}

sealed interface AppScreens {

    @Serializable
    data object OnboardingScreen : AppScreens

    @Serializable
    data object LoginScreen : AppScreens

    @Serializable
    data object HomeScreen : AppScreens

    @Serializable
    data object LikedCoursesScreen : AppScreens

    @Serializable
    data object AccountScreen : AppScreens
}