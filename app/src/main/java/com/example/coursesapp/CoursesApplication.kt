package com.example.coursesapp

import android.app.Application
import com.example.courses.di.coursesModule
import com.example.coursesapp.di.useCasesModule
import com.example.liked_courses.di.likedCoursesModule
import com.example.login.di.loginModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CoursesApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CoursesApplication)
            modules(
                coursesModule,
                likedCoursesModule,
                useCasesModule,
                loginModule
            )
        }
    }
}