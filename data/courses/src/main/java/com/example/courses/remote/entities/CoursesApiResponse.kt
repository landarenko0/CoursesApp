package com.example.courses.remote.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class CoursesApiResponse(@SerialName("courses") val courses: List<CourseApi>)
