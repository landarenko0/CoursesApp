package com.example.coursesapp.di

import com.example.coursesapp.activity.MainPresenter
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val appModule = module {

    factoryOf(::MainPresenter)
}