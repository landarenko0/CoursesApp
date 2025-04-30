package com.example.courses.di

import com.example.courses.remote.service.CourseService
import com.example.courses.remote.utils.BASE_URL
import com.example.courses.remote.utils.converterFactory
import com.example.courses.repository.CourseRepository
import com.example.courses.repository.CourseRepositoryImpl
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module
import retrofit2.Retrofit

val coursesDataModule = module {

    single<CourseRepository> {
        CourseRepositoryImpl(
            service = get(),
            dispatcher = Dispatchers.IO
        )
    }

    single<CourseService> {
        val retrofit = get<Retrofit>()
        retrofit.create(CourseService::class.java)
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(converterFactory)
            .build()
    }
}