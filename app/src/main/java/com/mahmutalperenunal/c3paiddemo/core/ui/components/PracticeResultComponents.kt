package com.mahmutalperenunal.c3paiddemo.core.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.mahmutalperenunal.c3paiddemo.core.ui.theme.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.stringResource
import com.mahmutalperenunal.c3paiddemo.R
import kotlin.math.roundToInt

@Composable
fun PracticeResultContent(
    modifier: Modifier = Modifier,
    score: Int,
    beforeText: String,
    beforeScore: Int,
    afterText: String,
    afterScore: Int,
    attemptLabel: String,
    nextStartsIn: Int,
    onTryAgain: () -> Unit,
) {
    // Re-animate the score whenever the result score changes.
    // % score animasyonu: 0 -> score (her Result girişinde 1 kere)
    val targetScore by rememberUpdatedState(score)
    val animatedScore = remember { Animatable(0f) }

    // Run the animation once per Result entry (keyed by targetScore).
    LaunchedEffect(targetScore) {
        animatedScore.snapTo(0f)
        animatedScore.animateTo(
            targetValue = targetScore.toFloat(),
            animationSpec = tween(durationMillis = 650)
        )
    }

    val scoreInt = animatedScore.value.roundToInt()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Spacer(Modifier.height(32.dp))

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(67.5.dp)
                    .background(PracticeResultScoreBg.copy(alpha = 0.5f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    // Animated percentage value.
                    text = stringResource(R.string.practice_result_score_percent, scoreInt),
                    fontWeight = FontWeight.W700,
                    fontSize = 21.sp,
                    lineHeight = 27.sp,
                    color = PracticeResultScoreGreen
                )
            }
        }

        Spacer(Modifier.height(34.dp))

        Text(
            text = stringResource(R.string.practice_result_good_try),
            modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.W400,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            color = PracticeResultGoodGreen
        )

        Spacer(Modifier.height(19.dp))

        Surface(
            color = PracticeResultPanelBg,
            shape = RoundedCornerShape(16.dp),
            tonalElevation = 0.dp,
            shadowElevation = 0.dp,
            modifier = Modifier
                .fillMaxWidth()
                .height(151.97.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(11.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.TrendingUp,
                        // Decorative icon; section title conveys meaning.
                        contentDescription = null,
                        tint = ReviewExplanationBody,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(Modifier.width(7.99.dp))
                    Text(
                        text = stringResource(R.string.practice_result_your_progress),
                        fontWeight = FontWeight.W700,
                        fontSize = 12.sp,
                        lineHeight = 16.sp,
                        color = ReviewExplanationBody
                    )
                }

                Surface(
                    color = PracticeResultPanelBg,
                    shape = RoundedCornerShape(10.dp),
                    tonalElevation = 0.dp,
                    shadowElevation = 0.dp,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(34.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                                Text(
                                    text = stringResource(R.string.practice_result_before),
                                    fontWeight = FontWeight.W400,
                                    fontSize = 12.sp,
                                    lineHeight = 16.sp,
                                    color = ReviewWrongRed
                                )
                                Text(
                                    text = beforeText,
                                    fontWeight = FontWeight.W700,
                                    fontSize = 12.sp,
                                    lineHeight = 16.sp,
                                    color = ReviewWrongRed
                                )
                            }
                            Text(
                                text = stringResource(R.string.practice_result_percent, beforeScore),
                                fontWeight = FontWeight.W700,
                                fontSize = 14.sp,
                                lineHeight = 20.sp,
                                color = ReviewWrongRed
                            )
                        }

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(PracticeResultDivider)
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(34.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                                Text(
                                    text = stringResource(R.string.practice_result_after),
                                    fontWeight = FontWeight.W400,
                                    fontSize = 12.sp,
                                    lineHeight = 16.sp,
                                    color = ReviewCorrectGreen
                                )
                                Text(
                                    text = afterText,
                                    fontWeight = FontWeight.W700,
                                    fontSize = 12.sp,
                                    lineHeight = 16.sp,
                                    color = ReviewCorrectGreen
                                )
                            }
                            Text(
                                text = stringResource(R.string.practice_result_percent, afterScore),
                                fontWeight = FontWeight.W700,
                                fontSize = 14.sp,
                                lineHeight = 20.sp,
                                color = ReviewCorrectGreen
                            )
                        }
                    }
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        Text(
            text = attemptLabel,
            modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.W500,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            color = ReviewDotNeutral
        )

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = onTryAgain,
            modifier = Modifier
                .fillMaxWidth()
                .height(36.dp),
            shape = RoundedCornerShape(1000.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PurplePrimary),
            contentPadding = PaddingValues(4.dp)
        ) {
            Text(
                text = stringResource(R.string.practice_result_try_again),
                fontWeight = FontWeight.W700,
                fontSize = 12.sp,
                lineHeight = 16.sp,
                color = Color.White
            )
        }

        Spacer(Modifier.height(40.dp))

        NextPracticeSection(
            nextStartsIn = nextStartsIn,
            lesson = stringResource(R.string.practice_next_lesson_1_greetings),
            title = stringResource(R.string.practice_next_title_good_morning),
            translated = stringResource(R.string.practice_next_translated_buenos_dias),
            phonetic = "/ɡʊd ˈmɔːrnɪŋ/",
        )
    }
}

@Composable
private fun NextPracticeSection(
    nextStartsIn: Int,
    lesson: String,
    title: String,
    translated: String,
    phonetic: String,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Text(
                text = stringResource(R.string.practice_next_section_title),
                fontWeight = FontWeight.W700,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                color = ReviewExplanationBody
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = stringResource(R.string.practice_next_starting_in),
                    fontWeight = FontWeight.W500,
                    fontSize = 11.sp,
                    lineHeight = 16.sp,
                    color = PurplePrimary
                )
                // Countdown value comes from ViewModel (auto-reset timer).
                CountdownBadge(value = nextStartsIn)
            }
        }

        NextLessonCard(
            lesson = lesson,
            title = title,
            translated = translated,
            phonetic = phonetic,
        )
    }
}

@Composable
private fun CountdownBadge(value: Int) {
    Box(
        modifier = Modifier.size(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .matchParentSize()
                .clip(CircleShape)
                .background(Color.Transparent)
                .border(2.2.dp, WordsCtaBg, CircleShape)
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .clip(CircleShape)
                .background(Color.Transparent)
                .border(1.6.dp, PurplePrimary, CircleShape)
        )
        Text(
            // Countdown number shown inside the badge.
            text = value.toString(),
            fontWeight = FontWeight.W500,
            fontSize = 11.sp,
            lineHeight = 0.sp,
            color = PurplePrimary
        )
    }
}

@Composable
private fun NextLessonCard(
    lesson: String,
    title: String,
    translated: String,
    phonetic: String,
) {
    Surface(
        color = SentEnd.copy(alpha = 0.95f),
        shape = RoundedCornerShape(12.dp),
        tonalElevation = 0.dp,
        shadowElevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .height(118.dp)
    ) {
        Row(Modifier.fillMaxSize()) {
            Box(
                Modifier
                    .width(1.6.dp)
                    .fillMaxHeight()
                    .background(PracticeGradientEnd)
            )

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 12.dp, top = 12.dp, end = 12.dp, bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Lesson pill chip (non-interactive in prototype).
                    Surface(
                        color = WordsCtaBg,
                        shape = RoundedCornerShape(9999.dp),
                        tonalElevation = 0.dp,
                        shadowElevation = 2.dp
                    ) {
                        Row(
                            modifier = Modifier.padding(start = 12.dp, top = 6.dp, end = 6.dp, bottom = 6.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = lesson,
                                fontWeight = FontWeight.W500,
                                fontSize = 11.sp,
                                lineHeight = 16.sp,
                                color = WordsLabel
                            )
                            Box(
                                modifier = Modifier
                                    .size(22.dp)
                                    .background(PurplePrimary.copy(alpha = 0.16f), CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.ChevronRight,
                                    // Decorative chevron.
                                    contentDescription = null,
                                    tint = PurplePrimary,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .background(ReviewSoundBg, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Filled.VolumeUp,
                                // Decorative audio icon.
                                contentDescription = null,
                                tint = SentEnd,
                                modifier = Modifier.size(24.dp)
                            )
                        }

                        Column {
                            Text(
                                text = title,
                                fontWeight = FontWeight.W700,
                                fontSize = 16.sp,
                                lineHeight = 24.sp,
                                color = SentCtaIcon
                            )

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = translated,
                                    fontWeight = FontWeight.W500,
                                    fontSize = 11.sp,
                                    lineHeight = 16.sp,
                                    color = ReviewTranslatedText
                                )
                                Spacer(Modifier.width(6.dp))
                                Box(Modifier.size(4.dp).background(ReviewDotNeutral, CircleShape))
                                Spacer(Modifier.width(6.dp))
                                Text(
                                    text = phonetic,
                                    fontWeight = FontWeight.W500,
                                    fontSize = 12.sp,
                                    lineHeight = 16.sp,
                                    color = TextSecondary
                                )
                            }
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .background(PracticeNextCtaBg.copy(alpha = 0.16f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.ChevronRight,
                        // Decorative chevron for next lesson CTA.
                        contentDescription = null,
                        tint = SentCtaIcon,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}