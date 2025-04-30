package com.example.auth.repository

import android.content.Context
import java.io.File

internal class AuthCacheRepositoryImpl(context: Context): AuthCacheRepository {

    private val cacheFile = File("${context.cacheDir.absolutePath}/$AUTH_CACHE_FILE_NAME")

    override fun checkUserLoggedIn(): Boolean {
        if (cacheFile.exists()) {
            val authResult = cacheFile.readText().toBooleanStrictOrNull()
            return authResult == true
        }

        return false
    }

    override fun setUserLoggedIn() {
        if (!cacheFile.exists()) cacheFile.createNewFile()

        cacheFile.writeText("true")
    }

    override fun setUserLoggedOut() {
        if (!cacheFile.exists()) cacheFile.createNewFile()

        cacheFile.writeText("false")
    }

    companion object {
        private const val AUTH_CACHE_FILE_NAME = "auth.txt"
    }
}