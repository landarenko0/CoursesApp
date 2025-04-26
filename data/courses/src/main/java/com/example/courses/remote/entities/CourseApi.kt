package com.example.courses.remote.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class CourseApi(
    @SerialName("id") val id: Long,
    @SerialName("title") val title: String,
    @SerialName("text") val text: String,
    @SerialName("price") val price: Float,
    @SerialName("rate") val rate: Float,
    @SerialName("startDate") val startDate: String,
    @SerialName("hasLike") val hasLike: Boolean,
    @SerialName("publishDate") val publishDate: String
)
