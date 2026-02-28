package com.mahmutalperenunal.c3paiddemo.features.progress.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mahmutalperenunal.c3paiddemo.R
import com.mahmutalperenunal.c3paiddemo.core.ui.components.*
import com.mahmutalperenunal.c3paiddemo.core.ui.components.ProgressCardDecor
import com.mahmutalperenunal.c3paiddemo.core.ui.components.EllipseDecor
import com.mahmutalperenunal.c3paiddemo.core.ui.components.ImageDecor
import com.mahmutalperenunal.c3paiddemo.core.ui.theme.*

@Composable
fun MyProgressRoute(
    onOpenNeedsReview: () -> Unit
) {
    // Decorative assets are defined per-card to match the exact Figma positioning.
    val wordsDecor = ProgressCardDecor(
        ellipses = listOf(
            EllipseDecor(
                size = DpSize(107.22.dp, 107.22.dp),
                offset = DpOffset(189.dp, 23.dp),
                drawableRes = R.drawable.progress_words_ellipse_1
            ),
            EllipseDecor(
                size = DpSize(88.22.dp, 88.22.dp),
                offset = DpOffset(246.dp, (-22).dp),
                drawableRes = R.drawable.progress_words_ellipse_2
            )
        ),
        images = listOf(
            ImageDecor(
                drawableRes = R.drawable.progress_words_illustration_1,
                size = DpSize(134.7.dp, 133.02.dp),
                offset = DpOffset(189.dp, (-14).dp),
                rotationDeg = 0f
            ),
            ImageDecor(
                drawableRes = R.drawable.progress_words_illustration_2,
                size = DpSize(70.33.dp, 36.36.dp),
                offset = DpOffset(273.dp, 11.dp),
                rotationDeg = 13.07f
            )
        )
    )

    val sentencesDecor = ProgressCardDecor(
        ellipses = listOf(
            EllipseDecor(
                size = DpSize(96.dp, 96.dp),
                offset = DpOffset(171.dp, 37.dp),
                drawableRes = R.drawable.progress_sentences_ellipse_1
            ),
            EllipseDecor(
                size = DpSize(105.dp, 105.dp),
                offset = DpOffset(229.dp, (-15).dp),
                drawableRes = R.drawable.progress_sentences_ellipse_2
            )
        ),
        images = listOf(
            ImageDecor(
                drawableRes = R.drawable.progress_sentences_illustration,
                size = DpSize(183.dp, 122.dp),
                offset = DpOffset(161.dp, (-3).dp)
            )
        )
    )

    val reviewDecor = ProgressCardDecor(
        ellipses = listOf(
            EllipseDecor(
                size = DpSize(96.dp, 96.dp),
                offset = DpOffset(214.dp, 21.dp),
                drawableRes = R.drawable.progress_review_ellipse
            )
        ),
        images = listOf(
            ImageDecor(
                drawableRes = R.drawable.progress_review_illustration,
                size = DpSize(159.dp, 156.41.dp),
                offset = DpOffset(177.dp, (-11).dp),
                rotationDeg = 3.98f
            )
        )
    )

    // Screen scaffold: gradient header + bottom pill nav + scrollable content.
    Scaffold(
        containerColor = ScreenBg,
        topBar = {
            GradientHeader(title = stringResource(id = R.string.my_progress_title))
        },
        bottomBar = {
            BottomPillNav(
                // No-op: this route is already on the Progress tab.
                onHomeClick = { /* TODO: navigate to Home when wired */ },
                // No-op: already on the selected destination.
                onProgressClick = { /* already here */ }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            // Bottom padding keeps the last items clear of the bottom navigation pill.
            contentPadding = PaddingValues(top = 22.dp, bottom = 120.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier = Modifier
                            .width(378.dp)
                            .height(24.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = stringResource(id = R.string.speaking_practice),
                            style = MaterialTheme.typography.titleMedium,
                            color = TextPrimary
                        )

                        // Decorative chevron; row text conveys meaning.
                        Icon(
                            imageVector = Icons.Filled.ChevronRight,
                            contentDescription = null,
                            tint = TextPrimary
                        )
                    }
                }
            }

            item {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    ProgressCard(
                        title = stringResource(id = R.string.my_progress_words_spoken),
                        value = stringResource(id = R.string.my_progress_value_placeholder),
                        titleColor = WordsLabel,
                        valueColor = WordsValue,
                        gradientStart = WordsStart,
                        gradientEnd = WordsEnd,
                        ctaBackground = WordsCtaBg,
                        ctaIconTint = WordsCtaIcon,
                        decor = wordsDecor
                    )
                }
            }

            item {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    ProgressCard(
                        title = stringResource(id = R.string.my_progress_sentences_spoken),
                        value = stringResource(id = R.string.my_progress_value_placeholder),
                        titleColor = SentText,
                        valueColor = SentText,
                        gradientStart = SentStart,
                        gradientEnd = SentEnd,
                        ctaBackground = SentCtaBg,
                        ctaIconTint = SentCtaIcon,
                        decor = sentencesDecor
                    )
                }
            }

            item {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    ProgressCard(
                        title = stringResource(id = R.string.my_progress_needs_review),
                        value = stringResource(id = R.string.my_progress_value_placeholder),
                        titleColor = ReviewLabel,
                        valueColor = ReviewValue,
                        gradientStart = ReviewStart,
                        gradientEnd = ReviewEnd,
                        ctaBackground = ReviewCtaBg,
                        ctaIconTint = ReviewCtaIcon,
                        // Only this card is interactive in the prototype; it navigates to Needs Review.
                        onClick = onOpenNeedsReview,
                        decor = reviewDecor
                    )
                }
            }

            item {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    DailyStreakCard(
                        modifier = Modifier.width(378.dp),
                        activeDays = 3
                    )
                }
            }
        }
    }
}