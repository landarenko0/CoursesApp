package com.example.liked_courses.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "liked_course")
internal data class LikedCourseDb(@ColumnInfo("course_id") val courseId: Long)
