package com.example.coursesapp.di

import com.example.auth.usecases.ValidateEmailAndPasswordUseCase
import com.example.auth.usecases.ValidateEmailUseCase
import com.example.auth.usecases.ValidatePasswordUseCase
import com.example.courses.usecases.GetAllCoursesUseCase
import com.example.courses.usecases.GetCourseByIdUseCase
import com.example.courses.usecases.GetCoursesByIdsUseCase
import com.example.liked_courses.usecases.GetLikedCoursesIdsUseCase
import com.example.liked_courses.usecases.MarkCourseAsLikedUseCase
import com.example.liked_courses.usecases.MarkCourseAsUnlikedUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val useCasesModule = module {

    factoryOf(::ValidateEmailAndPasswordUseCase)
    factoryOf(::ValidateEmailUseCase)
    factoryOf(::ValidatePasswordUseCase)

    factoryOf(::GetAllCoursesUseCase)
    factoryOf(::GetCourseByIdUseCase)
    factoryOf(::GetCoursesByIdsUseCase)

    factoryOf(::GetLikedCoursesIdsUseCase)
    factoryOf(::MarkCourseAsLikedUseCase)
    factoryOf(::MarkCourseAsUnlikedUseCase)
}