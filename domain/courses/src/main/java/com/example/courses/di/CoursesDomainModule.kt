package com.example.courses.di

import com.example.courses.usecases.GetAllCoursesUseCase
import com.example.courses.usecases.GetCourseByIdUseCase
import com.example.courses.usecases.GetCoursesByIdsUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val coursesDomainModule = module {

    factoryOf(::GetAllCoursesUseCase)
    factoryOf(::GetCourseByIdUseCase)
    factoryOf(::GetCoursesByIdsUseCase)
}