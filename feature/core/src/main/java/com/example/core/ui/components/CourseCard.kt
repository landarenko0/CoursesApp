package com.example.core.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.core.R
import com.example.core.domain.entities.CourseItem
import com.example.core.ui.theme.CoursesAppTheme
import com.example.core.ui.theme.Glass

@Composable
fun CourseCard(
    course: CourseItem,
    onClick: () -> Unit,
    onLikeClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        shape = MaterialTheme.shapes.small,
        modifier = modifier
    ) {
        CourseCardImage(
            hasLike = course.hasLike,
            rate = course.rate,
            date = course.startDate,
            onLikeClick = onLikeClick,
            modifier = Modifier.fillMaxWidth()
        )

        CourseDetails(
            title = course.title,
            text = course.text,
            price = course.price,
            modifier = Modifier.padding(dimensionResource(R.dimen.medium))
        )
    }
}

@Composable
private fun CourseCardImage(
    hasLike: Boolean,
    rate: String,
    date: String,
    onLikeClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(R.drawable.course_image),
            contentDescription = null,
            alignment = Alignment.TopCenter,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(dimensionResource(R.dimen.course_card_height))
                .clip(MaterialTheme.shapes.extraSmall)
                .fillMaxWidth()
        )

        LikeCard(
            onClick = onLikeClick,
            hasLike = hasLike,
            modifier = Modifier
                .padding(
                    top = dimensionResource(R.dimen.small),
                    end = dimensionResource(R.dimen.small)
                )
                .size(dimensionResource(R.dimen.like_container_size))
                .align(Alignment.TopEnd)
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.extra_small)),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(
                    start = dimensionResource(R.dimen.small),
                    bottom = dimensionResource(R.dimen.small)
                )
                .align(Alignment.BottomStart)
        ) {
            RateCard(rate = rate)
            DateCard(date = date)
        }
    }
}

@Composable
private fun CourseDetails(
    title: String,
    text: String,
    price: String,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.medium)),
        modifier = modifier
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.alpha(0.7f)
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.price, price),
                style = MaterialTheme.typography.titleMedium
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.extra_small)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.more_details),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary
                )

                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = null,
                    modifier = Modifier.size(dimensionResource(R.dimen.more_details_icon_size))
                )
            }
        }
    }
}

@Composable
private fun RateCard(
    rate: String,
    modifier: Modifier = Modifier
) {
    Card(
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.cardColors(containerColor = Glass),
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.small)),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(dimensionResource(R.dimen.small))
        ) {
            Icon(
                painter = painterResource(R.drawable.star),
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = null
            )

            Text(
                text = rate.toString(),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
private fun DateCard(
    date: String,
    modifier: Modifier = Modifier
) {
    Card(
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.cardColors(containerColor = Glass),
        modifier = modifier
    ) {
        Text(
            text = date,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(dimensionResource(R.dimen.small))
        )
    }
}

@Composable
private fun LikeCard(
    onClick: () -> Unit,
    hasLike: Boolean,
    modifier: Modifier = Modifier
) {
    val iconResId = if (hasLike) R.drawable.filled_bookmark else R.drawable.outlined_bookmark

    Card(
        onClick = onClick,
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = Glass),
        modifier = modifier
    ) {
        Box(Modifier.fillMaxSize()) {
            Icon(
                painter = painterResource(iconResId),
                tint = if (hasLike) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onPrimary,
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(dimensionResource(R.dimen.small))
            )
        }
    }
}

@Preview
@Composable
private fun CourseCardPreview() {
    CoursesAppTheme {
        CourseCard(
            course = CourseItem(
                id = 0,
                title = "Java-разработчик с нуля",
                text = "Освойте backend-разработку и программирование на Java, фреймворки Spring и Maven, работу с базами данных и API. Создайте свой собственный проект, собрав портфолио и став востребованным специалистом для любой IT компании.",
                price = "999",
                rate = "4.9",
                startDate = "22 Мая 2024",
                hasLike = true,
                publishDate = "22 Мая 2024"
            ),
            onClick = {},
            onLikeClick = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}