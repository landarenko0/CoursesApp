package com.example.auth.di

import com.example.auth.repository.AuthCacheRepository
import com.example.auth.repository.AuthCacheRepositoryImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val authDataModule = module {
    singleOf(::AuthCacheRepositoryImpl) { bind<AuthCacheRepository>() }
}