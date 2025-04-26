package com.example.courses.remote.service

import com.example.courses.remote.entities.CoursesApiResponse
import retrofit2.Response
import retrofit2.http.GET

internal interface CourseService {

    @GET("/db")
    fun getAllCourses(): Response<CoursesApiResponse>
}