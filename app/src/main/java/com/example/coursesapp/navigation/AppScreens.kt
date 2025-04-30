package com.example.coursesapp.navigation

import kotlinx.serialization.Serializable

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