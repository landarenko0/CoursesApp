package com.example.liked_courses.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.liked_courses.local.entities.LikedCourseDb
import kotlinx.coroutines.flow.Flow

@Dao
internal interface LikedCoursesDao {

    @Query("SELECT * FROM liked_course")
    fun getLikedCourses(): Flow<List<LikedCourseDb>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertLikedCourse(likedCourse: LikedCourseDb)

    @Delete
    suspend fun deleteLikedCourse(likedCourse: LikedCourseDb)
}