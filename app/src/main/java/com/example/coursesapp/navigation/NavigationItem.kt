package com.example.coursesapp.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class NavigationItem<T : Any>(
    @StringRes val titleRes: Int,
    @DrawableRes val iconRes: Int,
    val route: T
)