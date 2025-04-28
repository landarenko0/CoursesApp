package com.example.auth.usecases

class ValidateEmailAndPasswordUseCase(
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase
) {

    operator fun invoke(email: String, password: String): Result<Nothing?> {
        val emailValidateResult: Result<Nothing?> = validateEmailUseCase(email)
        val passwordValidateResult: Result<Nothing?> = validatePasswordUseCase(password)

        return when {
            emailValidateResult.isFailure -> Result.failure(emailValidateResult.exceptionOrNull()!!)
            passwordValidateResult.isFailure -> Result.failure(passwordValidateResult.exceptionOrNull()!!)

            else -> Result.success(null)
        }
    }
}