package com.example.auth.repository

interface AuthCacheRepository {

    fun checkUserLoggedIn(): Boolean

    fun setUserLoggedIn()

    fun setUserLoggedOut()
}