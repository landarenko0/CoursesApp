package com.example.courses.repository

import com.example.courses.entities.ApiResult
import com.example.courses.entities.Course
import com.example.courses.exceptions.ApiException
import com.example.courses.remote.entities.CoursesApiResponse
import com.example.courses.remote.mapper.toDomain
import com.example.courses.remote.service.CourseService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class CourseRepositoryImpl(private val service: CourseService): CourseRepository {

    override fun getAllCourses(): Flow<ApiResult<List<Course>>> = flow {
        emit(ApiResult.Loading)

        try {
            val serviceResponse = service.getAllCourses()
            val body = serviceResponse.body()

            if (serviceResponse.isSuccessful && body is CoursesApiResponse) {
                emit(ApiResult.Success(body.courses.map { it.toDomain() }))
            } else {
                val errorMessage = serviceResponse.errorBody()?.string()

                emit(ApiResult.Failure(ApiException(errorMessage)))
            }
        } catch (ex: Exception) {
            emit(ApiResult.Failure(ex))
        }
    }

    override fun getCoursesByIds(courseIds: List<Long>): Flow<ApiResult<List<Course>>> = flow {
        emit(ApiResult.Loading)

        try {
            val serviceResponse = service.getAllCourses()
            val body = serviceResponse.body()

            if (serviceResponse.isSuccessful && body is CoursesApiResponse) {
                emit(ApiResult.Success(body.courses.filter { it.id in courseIds }.map { it.toDomain() }))
            } else {
                val errorMessage = serviceResponse.errorBody()?.string()

                emit(ApiResult.Failure(ApiException(errorMessage)))
            }
        } catch (ex: Exception) {
            emit(ApiResult.Failure(ex))
        }
    }
}