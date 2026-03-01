package com.mahmutalperenunal.c3paiddemo.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GTranslate
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mahmutalperenunal.c3paiddemo.R
import com.mahmutalperenunal.c3paiddemo.core.ui.theme.*

@Composable
fun ReviewItemCard(
    title: String,
    translated: String,
    phonetic: String,
    youSaid: String,
    correct: String,
    explanation: String,
    isDone: Boolean,
    onToggleTranslate: () -> Unit,
    onPracticeAgain: () -> Unit,
    onMarkDone: () -> Unit,
) {
    val screenBg = ScreenBg
    val cardBg = SurfaceWhite

    // Text
    val titleColor = SentCtaIcon
    val translatedColor = ReviewTranslatedText
    val phoneticColor = TextSecondary

    // Header
    val leftBorder = ReviewLeftBorder
    val dotNeutral = ReviewDotNeutral
    val soundBg = ReviewSoundBg
    val soundIcon = SentEnd

    // Content
    val dividerColor = ReviewDivider
    val wrongColor = ReviewWrongRed
    val correctColor = ReviewCorrectGreen

    // Explanation
    val explanationDot = ReviewExplanationPurple
    val explanationText = ReviewExplanationPurple
    val explanationBody = ReviewExplanationBody

    // Translate CTA
    val translateContainer = ReviewTranslateContainer
    val translateIcon = ReviewTranslateIcon

    // Buttons
    val practiceBtnBg = NavHomeTint
    val practiceBtnText = ReviewPracticeBtnText
    val doneBtnBg = ReviewDoneBtnBg
    val doneBtnText = SurfaceWhite

    val contentRadius = 10.dp

    val cardRadius = 14.dp
    val padding = 16.dp
    val gap = 16.dp

    val leftBorderWidth = 2.4.dp

    val btnHeight = 40.dp
    val btnRadius = 1000.dp

    val shape = RoundedCornerShape(cardRadius)

    Surface(
        shape = shape,
        color = cardBg,
        tonalElevation = 0.dp,
        shadowElevation = 1.dp,
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape)
    ) {
        Row(Modifier.fillMaxWidth()) {
            Box(
                Modifier
                    .width(leftBorderWidth)
                    .fillMaxHeight()
                    .background(leftBorder)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(padding),
                verticalArrangement = Arrangement.spacedBy(gap)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Decorative sound button (prototype-only; no real playback).
                    SoundIconWithWaveOnce(
                        modifier = Modifier.size(40.dp),
                        backgroundColor = soundBg,
                        iconTint = soundIcon,
                    )

                    Spacer(Modifier.width(8.dp))

                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = title,
                            fontWeight = FontWeight.W700,
                            fontSize = 16.sp,
                            lineHeight = 24.sp,
                            color = titleColor
                        )

                        Spacer(Modifier.height(2.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = translated,
                                fontWeight = FontWeight.W500,
                                fontSize = 12.sp,
                                lineHeight = 16.sp,
                                color = translatedColor
                            )

                            Spacer(Modifier.width(6.dp))

                            Box(
                                Modifier
                                    .size(4.dp)
                                    .background(dotNeutral, CircleShape)
                            )

                            Spacer(Modifier.width(6.dp))

                            Text(
                                text = phonetic,
                                fontWeight = FontWeight.W500,
                                fontSize = 12.sp,
                                lineHeight = 16.sp,
                                color = phoneticColor
                            )
                        }
                    }
                }

                Surface(
                    shape = RoundedCornerShape(contentRadius),
                    color = screenBg,
                    tonalElevation = 0.dp,
                    shadowElevation = 0.dp,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                            Text(
                                text = stringResource(R.string.review_item_you_said),
                                fontWeight = FontWeight.W400,
                                fontSize = 12.sp,
                                lineHeight = 16.sp,
                                color = wrongColor
                            )
                            Text(
                                text = youSaid,
                                fontWeight = FontWeight.W700,
                                fontSize = 24.sp,
                                lineHeight = 32.sp,
                                color = wrongColor
                            )
                        }

                        Box(
                            Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(dividerColor)
                        )

                        Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                            Text(
                                text = stringResource(R.string.review_item_correct),
                                fontWeight = FontWeight.W400,
                                fontSize = 12.sp,
                                lineHeight = 16.sp,
                                color = correctColor
                            )
                            Text(
                                text = correct,
                                fontWeight = FontWeight.W700,
                                fontSize = 16.sp,
                                lineHeight = 24.sp,
                                color = correctColor
                            )
                        }
                    }
                }

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            modifier = Modifier.weight(1f),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                Modifier
                                    .size(4.dp)
                                    .background(explanationDot, CircleShape)
                            )
                            Spacer(Modifier.width(4.dp))
                            Text(
                                text = stringResource(R.string.review_item_explanation),
                                fontWeight = FontWeight.W700,
                                fontSize = 12.sp,
                                lineHeight = 16.sp,
                                color = explanationText
                            )
                        }

                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .background(translateContainer, CircleShape)
                                // Toggles translated state for this item.
                                .clickable { onToggleTranslate() },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Filled.GTranslate,
                                contentDescription = null,
                                tint = translateIcon,
                                modifier = Modifier.size(12.dp)
                            )
                        }
                    }

                    Text(
                        text = explanation,
                        fontWeight = FontWeight.W400,
                        fontSize = 12.sp,
                        lineHeight = 16.sp,
                        color = explanationBody,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Button(
                        // Navigates to Practice Pronunciation flow.
                        onClick = onPracticeAgain,
                        modifier = Modifier
                            .weight(1f)
                            .height(btnHeight),
                        shape = RoundedCornerShape(btnRadius),
                        colors = ButtonDefaults.buttonColors(containerColor = practiceBtnBg),
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.review_item_practice_again),
                            fontWeight = FontWeight.W700,
                            fontSize = 12.sp,
                            lineHeight = 16.sp,
                            color = practiceBtnText
                        )
                    }

                    Button(
                        onClick = onMarkDone,
                        // Disable once done to prevent duplicate actions.
                        enabled = !isDone,
                        modifier = Modifier
                            .weight(1f)
                            .height(btnHeight),
                        shape = RoundedCornerShape(btnRadius),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = doneBtnBg,
                            disabledContainerColor = doneBtnBg.copy(alpha = 0.5f)
                        ),
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = if (isDone) stringResource(R.string.review_item_done)
                            else stringResource(R.string.review_item_mark_as_done),
                            fontWeight = FontWeight.W700,
                            fontSize = 12.sp,
                            lineHeight = 16.sp,
                            color = doneBtnText
                        )
                    }
                }
            }
        }
    }
}

// Simple sound icon button used in ReviewItemCard; kept local for reuse and clarity.
@Composable
private fun SoundIconWithWaveOnce(
    modifier: Modifier = Modifier,
    backgroundColor: androidx.compose.ui.graphics.Color,
    iconTint: androidx.compose.ui.graphics.Color,
) {
    Box(
        modifier = modifier
            .background(backgroundColor, CircleShape)
            // Click is intentionally a no-op in prototype.
            .clickable { /* TODO: play audio */ },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Filled.VolumeUp,
            // Decorative icon; card text provides context.
            contentDescription = null,
            tint = iconTint,
            modifier = Modifier.size(24.dp)
        )
    }
}