package com.example.liked_courses.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "liked_course")
internal data class LikedCourseDb(
    @PrimaryKey
    @ColumnInfo("course_id")
    val courseId: Long
)
