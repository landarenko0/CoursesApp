package com.example.onboarding.data.repository

import android.content.Context
import com.example.onboarding.domain.repository.OnboardingRepository
import java.io.File

internal class OnboardingRepositoryImpl(context: Context) : OnboardingRepository {

    private val cacheFile = File("${context.cacheDir.absolutePath}/$ONBOARDING_CACHE_FILE_NAME")

    override fun checkUserClosedOnboardingScreen(): Boolean = cacheFile.exists()

    override fun setUserClosedOnboardingScreen() {
        if (!cacheFile.exists()) cacheFile.createNewFile()
    }

    companion object {
        private const val ONBOARDING_CACHE_FILE_NAME = "onboarding.txt"
    }
}