package com.example.coursesapp.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.core.ui.theme.DarkGrey
import com.example.home.ui.HomeRoot
import com.example.login.ui.LoginRoot
import com.example.onboarding.ui.OnboardingRoot
import com.example.core.R as coreR

private val navigationItems = listOf(
    NavigationItem(
        titleRes = coreR.string.main,
        iconRes = coreR.drawable.home_icon,
        route = AppScreens.HomeScreen
    ),
    NavigationItem(
        titleRes = coreR.string.liked_courses,
        iconRes = coreR.drawable.outlined_bookmark,
        route = AppScreens.LikedCoursesScreen
    ),
    NavigationItem(
        titleRes = coreR.string.account,
        iconRes = coreR.drawable.account_icon,
        route = AppScreens.AccountScreen
    )
)

@Composable
fun CoursesAppNavGraph(startScreen: AppScreens) {
    val navController = rememberNavController()

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = Screen.getScreenByRoute(backStackEntry?.destination?.route)

    val showBottomBar = currentScreen in listOf(Screen.HOME, Screen.LIKED_COURSES, Screen.ACCOUNT)

    val selectedNavigationItemIndex = when (currentScreen) {
        Screen.HOME -> 0
        Screen.LIKED_COURSES -> 1
        Screen.ACCOUNT -> 2
        else -> 3
    }

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar(
                    containerColor = DarkGrey
                ) {
                    navigationItems.forEachIndexed { index, item ->
                        NavigationBarItem(
                            label = {
                                Text(
                                    text = stringResource(item.titleRes),
                                    style = MaterialTheme.typography.labelSmall
                                )
                            },
                            selected = selectedNavigationItemIndex == index,
                            onClick = {
                                navController.navigate(item.route) {
                                    popUpTo(AppScreens.HomeScreen) { saveState = true }

                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = {
                                Icon(
                                    painter = painterResource(item.iconRes),
                                    modifier = Modifier.size(
                                        dimensionResource(coreR.dimen.navigation_item_size)
                                    ),
                                    contentDescription = stringResource(item.titleRes)
                                )
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = MaterialTheme.colorScheme.primary,
                                selectedTextColor = MaterialTheme.colorScheme.primary,
                                unselectedIconColor = MaterialTheme.colorScheme.onPrimary,
                                unselectedTextColor = MaterialTheme.colorScheme.onPrimary,
                                indicatorColor = MaterialTheme.colorScheme.surfaceContainerHighest
                            )
                        )
                    }
                }
            }
        },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = startScreen,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            composable<AppScreens.OnboardingScreen> {
                OnboardingRoot(
                    onContinueButtonClick = { navController.navigate(AppScreens.LoginScreen) }
                )
            }

            composable<AppScreens.LoginScreen> {
                LoginRoot(
                    onLoginButtonClick = {
                        navController.navigate(AppScreens.HomeScreen) {
                            navController.popBackStack(
                                route = AppScreens.OnboardingScreen,
                                inclusive = true
                            )
                        }
                    }
                )
            }

            composable<AppScreens.HomeScreen> {
                HomeRoot()
            }

            composable<AppScreens.LikedCoursesScreen> { }

            composable<AppScreens.AccountScreen> { }
        }
    }
}