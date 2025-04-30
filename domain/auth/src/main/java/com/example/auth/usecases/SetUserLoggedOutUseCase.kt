package com.example.auth.usecases

import com.example.auth.repository.AuthCacheRepository

class SetUserLoggedOutUseCase(
    private val repository: AuthCacheRepository
) {

    operator fun invoke() = repository.setUserLoggedOut()
}