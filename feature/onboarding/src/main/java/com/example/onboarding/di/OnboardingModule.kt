package com.example.onboarding.di

import com.example.onboarding.data.repository.OnboardingRepositoryImpl
import com.example.onboarding.domain.repository.OnboardingRepository
import com.example.onboarding.domain.usecases.CheckUserClosedOnboardingScreenUseCase
import com.example.onboarding.domain.usecases.SetUserClosedOnboardingScreenUseCase
import com.example.onboarding.ui.OnboardingViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val onboardingModule = module {
    singleOf(::OnboardingRepositoryImpl) { bind<OnboardingRepository>() }

    viewModelOf(::OnboardingViewModel)

    factoryOf(::CheckUserClosedOnboardingScreenUseCase)
    factoryOf(::SetUserClosedOnboardingScreenUseCase)
}