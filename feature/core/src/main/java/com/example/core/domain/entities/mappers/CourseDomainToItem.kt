package com.example.core.domain.entities.mappers

import com.example.core.domain.entities.CourseItem
import com.example.courses.entities.Course

fun Course.toItem() = CourseItem(
    id = this.id,
    title = this.title,
    text = this.text,
    price = this.price,
    rate = this.rate,
    startDate = this.startDate,
    hasLike = this.hasLike,
    publishDate = this.publishDate
)