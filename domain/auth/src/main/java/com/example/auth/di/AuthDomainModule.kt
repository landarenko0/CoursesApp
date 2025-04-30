package com.example.auth.di

import com.example.auth.usecases.CheckUserLoggedInUseCase
import com.example.auth.usecases.SetUserLoggedInUseCase
import com.example.auth.usecases.SetUserLoggedOutUseCase
import com.example.auth.usecases.ValidateEmailAndPasswordUseCase
import com.example.auth.usecases.ValidateEmailUseCase
import com.example.auth.usecases.ValidatePasswordUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val authDomainModule = module {

    factoryOf(::CheckUserLoggedInUseCase)
    factoryOf(::SetUserLoggedInUseCase)
    factoryOf(::SetUserLoggedOutUseCase)
    factoryOf(::ValidateEmailAndPasswordUseCase)
    factoryOf(::ValidateEmailUseCase)
    factoryOf(::ValidatePasswordUseCase)
}