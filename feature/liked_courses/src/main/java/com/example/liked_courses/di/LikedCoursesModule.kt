package com.example.liked_courses.di

import com.example.liked_courses.ui.LikedCoursesViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val likedCoursesModule = module {
    viewModelOf(::LikedCoursesViewModel)
}