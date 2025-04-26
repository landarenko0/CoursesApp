package com.example.liked_courses.di

import com.example.liked_courses.local.dao.LikedCoursesDao
import com.example.liked_courses.local.database.LikedCoursesDatabase
import com.example.liked_courses.repository.LikedCoursesRepository
import com.example.liked_courses.repository.LikedCoursesRepositoryImpl
import com.example.liked_courses.usecases.GetLikedCoursesIdsUseCase
import com.example.liked_courses.usecases.MarkCourseAsLikedUseCase
import com.example.liked_courses.usecases.MarkCourseAsUnlikedUseCase
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val likedCoursesModule = module {

    single<LikedCoursesRepository> {
        LikedCoursesRepositoryImpl(
            likedCoursesDao = get(),
            dispatcher = Dispatchers.IO
        )
    }

    single<LikedCoursesDao> {
        val database = get<LikedCoursesDatabase>()
        database.likedCoursesDao()
    }

    single<LikedCoursesDatabase> {
        LikedCoursesDatabase.build(context = get())
    }

    factory<GetLikedCoursesIdsUseCase> { GetLikedCoursesIdsUseCase(repository = get()) }

    factory<MarkCourseAsLikedUseCase> { MarkCourseAsLikedUseCase(repository = get()) }

    factory<MarkCourseAsUnlikedUseCase> { MarkCourseAsUnlikedUseCase(repository = get()) }
}