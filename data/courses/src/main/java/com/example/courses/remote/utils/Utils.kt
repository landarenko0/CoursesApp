package com.example.courses.remote.utils

import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.converter.kotlinx.serialization.asConverterFactory

internal const val BASE_URL = "https://my-json-server.typicode.com"

internal val converterFactory = Json.asConverterFactory(MediaType.get("application/json; charset=UTF8"))