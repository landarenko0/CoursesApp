package com.example.liked_courses.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.liked_courses.local.dao.LikedCoursesDao
import com.example.liked_courses.local.entities.LikedCourseDb

@Database(
    entities = [LikedCourseDb::class],
    version = 1
)
internal abstract class LikedCoursesDatabase : RoomDatabase() {

    abstract fun likedCoursesDao(): LikedCoursesDao

    companion object {

        private const val DATABASE_NAME = "liked_courses.db"

        fun build(context: Context): LikedCoursesDatabase = Room.databaseBuilder(
            context = context,
            klass = LikedCoursesDatabase::class.java,
            name = DATABASE_NAME
        ).build()
    }
}