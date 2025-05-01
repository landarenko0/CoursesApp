package com.example.coursesapp

import android.app.Application
import com.example.account.di.accountModule
import com.example.auth.di.authDataModule
import com.example.auth.di.authDomainModule
import com.example.courses.di.coursesDataModule
import com.example.courses.di.coursesDomainModule
import com.example.coursesapp.di.appModule
import com.example.home.di.homeModule
import com.example.liked_courses.di.likedCoursesDataModule
import com.example.liked_courses.di.likedCoursesDomainModule
import com.example.liked_courses.di.likedCoursesModule
import com.example.login.di.loginModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CoursesApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CoursesApplication)

            modules(appModule)

            // data modules
            modules(
                coursesDataModule,
                likedCoursesDataModule,
                authDataModule
            )

            // domain modules
            modules(
                authDomainModule,
                coursesDomainModule,
                likedCoursesDomainModule
            )

            // feature modules
            modules(
                loginModule,
                homeModule,
                likedCoursesModule,
                accountModule
            )
        }
    }
}