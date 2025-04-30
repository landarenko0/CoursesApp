package com.example.auth.usecases

import com.example.auth.repository.AuthCacheRepository

class CheckUserLoggedInUseCase(
    private val repository: AuthCacheRepository
) {

    operator fun invoke(): Boolean = repository.checkUserLoggedIn()
}