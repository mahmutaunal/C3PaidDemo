package com.mahmutalperenunal.c3paiddemo.core.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mahmutalperenunal.c3paiddemo.R
import com.mahmutalperenunal.c3paiddemo.core.ui.theme.*

@Composable
fun DailyStreakCard(
    modifier: Modifier = Modifier,
    activeDays: Int = 3
) {
    // Card corner radius per Figma.
    val shape = RoundedCornerShape(8.dp)
    // Localized day labels (no hardcoded strings).
    val days = listOf(
        stringResource(id = R.string.day_sun),
        stringResource(id = R.string.day_mon),
        stringResource(id = R.string.day_tue),
        stringResource(id = R.string.day_wed),
        stringResource(id = R.string.day_thu),
        stringResource(id = R.string.day_fri),
        stringResource(id = R.string.day_sat)
    )

    Column(modifier = modifier) {
        Text(
            text = stringResource(id = R.string.daily_streak_title),
            style = MaterialTheme.typography.titleMedium,
            color = TextPrimary
        )
        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                // Fixed height to match the designed card proportions.
                .height(87.dp)
                // Shadow is drawn outside the clipped shape for a softer elevation.
                .shadow(2.dp, shape, clip = false)
                .clip(shape)
                .background(SurfaceWhite)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                days.forEachIndexed { index, day ->
                    // First `activeDays` stamps are highlighted (e.g., streak length).
                    val isActive = index < activeDays

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                // Stamp background changes based on active/inactive state.
                                .background(if (isActive) StreakStampBg else StreakStampOffBg),
                            contentAlignment = Alignment.Center
                        ) {
                            // Swap between active/inactive flame assets.
                            val iconRes = if (isActive) {
                                R.drawable.ic_streak_flame_active
                            } else {
                                R.drawable.ic_streak_flame_inactive
                            }

                            Image(
                                painter = painterResource(id = iconRes),
                                // Decorative icon; day label conveys meaning.
                                contentDescription = null,
                                contentScale = ContentScale.Fit,
                                modifier = Modifier.size(18.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = day,
                            style = MaterialTheme.typography.labelSmall,
                            color = if (isActive) StreakDay else StreakDayDisabled
                        )
                    }
                }
            }
        }
    }
}