package com.example.courses.remote.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class CourseApi(
    @SerialName("id") val id: Long,
    @SerialName("title") val title: String,
    @SerialName("text") val text: String,
    @SerialName("price") val price: String,
    @SerialName("rate") val rate: String,
    @SerialName("startDate") val startDate: String,
    @SerialName("hasLike") val hasLike: Boolean,
    @SerialName("publishDate") val publishDate: String
)
