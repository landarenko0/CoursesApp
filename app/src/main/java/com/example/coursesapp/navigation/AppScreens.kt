package com.example.coursesapp.navigation

import kotlinx.serialization.Serializable

@Serializable
data object OnboardingScreen

@Serializable
data object LoginScreen

@Serializable
data object HomeScreen

@Serializable
data class CourseScreen(val courseId: Long)

@Serializable
data object LikedCoursesScreen

@Serializable
data object AccountScreen