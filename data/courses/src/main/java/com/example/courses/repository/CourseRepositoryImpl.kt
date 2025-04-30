package com.example.courses.repository

import com.example.courses.entities.Course
import com.example.courses.exceptions.ApiException
import com.example.courses.remote.entities.CoursesApiResponse
import com.example.courses.remote.mapper.toDomain
import com.example.courses.remote.service.CourseService

internal class CourseRepositoryImpl(private val service: CourseService): CourseRepository {

    override suspend fun getAllCourses(): Result<List<Course>> {
        return try {
            val serviceResponse = service.getAllCourses()
            val body = serviceResponse.body()

            if (serviceResponse.isSuccessful && body is CoursesApiResponse) {
                Result.success(body.courses.map { it.toDomain() })
            } else {
                val errorMessage = serviceResponse.errorBody()?.string()

                Result.failure(ApiException(errorMessage))
            }
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }

    override suspend fun getCoursesByIds(courseIds: List<Long>): Result<List<Course>> {
        return try {
            val serviceResponse = service.getAllCourses()
            val body = serviceResponse.body()

            if (serviceResponse.isSuccessful && body is CoursesApiResponse) {
                Result.success(body.courses.filter { it.id in courseIds }.map { it.toDomain() })
            } else {
                val errorMessage = serviceResponse.errorBody()?.string()

                Result.failure(ApiException(errorMessage))
            }
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }

    override suspend fun getCourseById(courseId: Long): Result<Course?> {
        return try {
            val serviceResponse = service.getAllCourses()
            val body = serviceResponse.body()

            if (serviceResponse.isSuccessful && body is CoursesApiResponse) {
                Result.success(body.courses.find { it.id == courseId }?.toDomain())
            } else {
                val errorMessage = serviceResponse.errorBody()?.string()

                Result.failure(ApiException(errorMessage))
            }
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }
}