package com.mahmutalperenunal.c3paiddemo.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mahmutalperenunal.c3paiddemo.core.ui.theme.PracticeProgressPrimary
import com.mahmutalperenunal.c3paiddemo.core.ui.theme.PracticeProgressTrack

// Represents the two visual states of the top progress bar in the practice flow.
enum class PracticeTopProgressMode { IDLE, LISTENING }

@Composable
fun PracticeTopProgress(
    mode: PracticeTopProgressMode,
    modifier: Modifier = Modifier,
    totalWidth: Dp = 378.dp,
    gap: Dp = 8.dp,
    idleActiveWidth: Dp = 168.dp,
    listeningActiveWidth: Dp = 300.dp,
) {
    val primary = PracticeProgressPrimary
    val track = PracticeProgressTrack

    // Active segment width changes depending on the current screen state.
    val activeWidth = when (mode) {
        PracticeTopProgressMode.IDLE -> idleActiveWidth
        PracticeTopProgressMode.LISTENING -> listeningActiveWidth
    }

    val minTrackWidth = 52.dp
    // Remaining track width is clamped to avoid collapsing below design minimum.
    val remaining = (totalWidth - activeWidth - gap).coerceAtLeast(minTrackWidth)

    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .width(totalWidth)
                .height(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .width(activeWidth)
                    .height(12.dp)
                    .clip(RoundedCornerShape(24.dp))
                    // Filled (active) portion of the progress bar.
                    .background(primary)
            )

            Spacer(Modifier.width(gap))

            Box(
                modifier = Modifier
                    .width(remaining)
                    .height(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(RoundedCornerShape(4.dp))
                        // Inactive track portion.
                        .background(track)
                )

                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .align(Alignment.CenterEnd)
                ) {
                    Box(
                        modifier = Modifier
                            .size(4.dp)
                            .align(Alignment.Center)
                            .clip(RoundedCornerShape(3.dp))
                            // Small accent dot at the end of the track.
                            .background(primary)
                    )
                }
            }
        }
    }
}

// Result state shows a fully completed progress bar.
@Composable
fun PracticeTopProgressResult(
    modifier: Modifier = Modifier,
) {
    val primary = PracticeProgressPrimary

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(12.dp)
            .padding(horizontal = 4.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(primary)
        )
    }
}