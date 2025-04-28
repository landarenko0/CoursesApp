package com.example.auth.usecases

import com.example.auth.usecases.exceptions.EmptyFieldException

class ValidatePasswordUseCase {

    operator fun invoke(password: String): Result<Nothing?> {
        return when {
            password.isEmpty() -> Result.failure(EmptyFieldException())

            else -> Result.success(null)
        }
    }
}