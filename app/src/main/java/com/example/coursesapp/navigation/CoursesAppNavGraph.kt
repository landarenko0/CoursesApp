package com.example.coursesapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.home.ui.HomeRoot
import com.example.login.ui.LoginRoot
import com.example.onboarding.ui.OnboardingRoot

@Composable
fun CoursesAppNavGraph(startScreen: AppScreens) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startScreen
    ) {
        composable<AppScreens.OnboardingScreen> {
            OnboardingRoot(
                onContinueButtonClick = { navController.navigate(AppScreens.LoginScreen) }
            )
        }

        composable<AppScreens.LoginScreen> {
            LoginRoot(
                onLoginButtonClick = { navController.navigate(AppScreens.HomeScreen) }
            )
        }

        composable<AppScreens.HomeScreen> {
            HomeRoot()
        }
    }
}