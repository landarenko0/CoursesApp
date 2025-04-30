package com.example.liked_courses.di

import com.example.liked_courses.usecases.GetLikedCoursesIdsUseCase
import com.example.liked_courses.usecases.MarkCourseAsLikedUseCase
import com.example.liked_courses.usecases.MarkCourseAsUnlikedUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val likedCoursesDomainModule = module {

    factoryOf(::GetLikedCoursesIdsUseCase)
    factoryOf(::MarkCourseAsLikedUseCase)
    factoryOf(::MarkCourseAsUnlikedUseCase)
}