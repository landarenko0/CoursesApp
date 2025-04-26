package com.example.courses.remote.utils

import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.converter.kotlinx.serialization.asConverterFactory

const val BASE_URL = "my-json-server.typicode.com/landarenko0/coursesapp"

val converterFactory = Json.asConverterFactory(MediaType.get("application/json; charset=UTF8"))