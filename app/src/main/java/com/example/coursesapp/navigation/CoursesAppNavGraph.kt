package com.example.coursesapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.login.ui.LoginRoot
import com.example.onboarding.ui.OnboardingRoot

@Composable
fun CoursesAppNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = OnboardingScreen
    ) {
        composable<OnboardingScreen> {
            OnboardingRoot(
                onContinueButtonClick = { navController.navigate(LoginScreen) }
            )
        }

        composable<LoginScreen> {
            LoginRoot(
                onLoginButtonClick = {}
            )
        }
    }
}