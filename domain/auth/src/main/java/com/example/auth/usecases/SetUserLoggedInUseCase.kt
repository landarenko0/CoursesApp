package com.example.auth.usecases

import com.example.auth.repository.AuthCacheRepository

class SetUserLoggedInUseCase(
    private val repository: AuthCacheRepository
) {

    operator fun invoke() = repository.setUserLoggedIn()
}