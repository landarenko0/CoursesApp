package com.example.onboarding.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntSize
import com.example.core.ui.theme.Glass
import com.example.core.R as coreR
import com.example.onboarding.R as onboardingR

private data class CourseCard(
    @StringRes val courseNameStringResId: Int,
    val angle: Float = 0f,
    val activated: Boolean = false
)

private val courseNamesLists = listOf(
    listOf(
        CourseCard(onboardingR.string.one_s_administration),
        CourseCard(onboardingR.string.rabbitmq, angle = -15f, activated = true),
        CourseCard(onboardingR.string.traffic)
    ),
    listOf(
        CourseCard(onboardingR.string.content_marketing),
        CourseCard(onboardingR.string.b2b_marketing),
        CourseCard(onboardingR.string.google_analytics)
    ),
    listOf(
        CourseCard(onboardingR.string.ux_researcher),
        CourseCard(onboardingR.string.web_analytics),
        CourseCard(onboardingR.string.big_data, angle = 15f, activated = true)
    ),
    listOf(
        CourseCard(onboardingR.string.game_design),
        CourseCard(onboardingR.string.web_design),
        CourseCard(onboardingR.string.cinema_4d),
        CourseCard(onboardingR.string.prompt_engineering)
    ),
    listOf(
        CourseCard(onboardingR.string.webflow),
        CourseCard(onboardingR.string.three_js, angle = -15f, activated = true),
        CourseCard(onboardingR.string.parsing),
        CourseCard(onboardingR.string.python_development)
    )
)

@Composable
internal fun CoursesRows(modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState(initial = 500)

    var containerSize by remember { mutableStateOf(IntSize.Zero) }
    var contentSize by remember { mutableStateOf(IntSize.Zero) }

    Box(
        modifier = modifier.onGloballyPositioned { coordinates ->
            containerSize = coordinates.size
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(dimensionResource(coreR.dimen.extra_small)),
            modifier = Modifier
                .horizontalScroll(scrollState)
                .onGloballyPositioned { coordinates ->
                    contentSize = coordinates.size
                }
                .align(Alignment.Center)
        ) {
            courseNamesLists.forEach { courseNames ->
                CourseRow(courseCards = courseNames)
            }
        }
    }

    LaunchedEffect(containerSize, contentSize) {
        if (contentSize.width > containerSize.width) {
            val contentCenter = (contentSize.width - containerSize.width) / 2
            scrollState.scrollTo(contentCenter)
        }
    }
}

@Composable
private fun CourseRow(
    courseCards: List<CourseCard>,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(coreR.dimen.extra_small)),
        modifier = modifier
    ) {
        courseCards.forEach { courseCard ->
            CourseItem(courseCard = courseCard)
        }
    }
}

@Composable
private fun CourseItem(
    courseCard: CourseCard,
    modifier: Modifier = Modifier
) {
    Card(
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = if (courseCard.activated) MaterialTheme.colorScheme.primary else Glass
        ),
        modifier = modifier.rotate(courseCard.angle)
    ) {
        Text(
            text = stringResource(courseCard.courseNameStringResId),
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(
                vertical = dimensionResource(coreR.dimen.large),
                horizontal = dimensionResource(coreR.dimen.extra_large)
            )
        )
    }
}