package com.example.auth.usecases

import com.example.auth.usecases.exceptions.EmptyFieldException
import com.example.auth.usecases.exceptions.InvalidEmailException

class ValidateEmailUseCase {

    operator fun invoke(email: String): Result<Nothing?> {
        val emailRegex = "([a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\\.[a-zA-Z0-9_-]+)".toRegex()

        return when {
            email.isEmpty() -> Result.failure(EmptyFieldException())
            !email.matches(emailRegex) -> Result.failure(InvalidEmailException())

            else -> Result.success(null)
        }
    }
}