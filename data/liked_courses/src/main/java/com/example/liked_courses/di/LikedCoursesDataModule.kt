package com.example.liked_courses.di

import com.example.liked_courses.local.dao.LikedCoursesDao
import com.example.liked_courses.local.database.LikedCoursesDatabase
import com.example.liked_courses.repository.LikedCoursesRepository
import com.example.liked_courses.repository.LikedCoursesRepositoryImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val likedCoursesDataModule = module {

    singleOf(::LikedCoursesRepositoryImpl) { bind<LikedCoursesRepository>() }

    single<LikedCoursesDao> {
        val database = get<LikedCoursesDatabase>()
        database.likedCoursesDao()
    }

    single<LikedCoursesDatabase> {
        LikedCoursesDatabase.build(context = get())
    }
}