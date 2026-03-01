package com.mahmutalperenunal.c3paiddemo.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text
import com.mahmutalperenunal.c3paiddemo.core.ui.theme.PurplePrimary
import com.mahmutalperenunal.c3paiddemo.core.ui.theme.NavHomeTint
import com.mahmutalperenunal.c3paiddemo.core.ui.theme.SurfaceWhite
import com.mahmutalperenunal.c3paiddemo.features.review.ui.NeedsReviewViewModel

// Tab strip component used in Needs Review screen (visual parity with prototype).
@Composable
fun NeedsReviewTabs(
    selectedTab: NeedsReviewViewModel.Tab,
    onTabSelected: (NeedsReviewViewModel.Tab) -> Unit,
    enabled: Boolean,
    practiceHistoryLabel: String,
    needsReviewLabel: String,
    modifier: Modifier = Modifier
) {
    // Color palette aligned with header styling from the design.
    val purple = PurplePrimary
    val unselectedText = NavHomeTint
    val selectedText = SurfaceWhite
    val indicator = NavHomeTint

    // Fixed tab height based on Figma measurements.
    val tabHeight = 52.dp
    val indicatorHeight = 4.dp

    // Container provides purple background and vertical layout (tabs + indicator).
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(purple)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(tabHeight),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Left tab
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    // Click handling is externally controlled (disabled in prototype).
                    .clickable(enabled = enabled) { onTabSelected(NeedsReviewViewModel.Tab.PRACTICE_HISTORY) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = practiceHistoryLabel,
                    fontWeight = if (selectedTab == NeedsReviewViewModel.Tab.PRACTICE_HISTORY) FontWeight.W700 else FontWeight.W400,
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    color = if (selectedTab == NeedsReviewViewModel.Tab.PRACTICE_HISTORY) selectedText else unselectedText
                )
            }

            // Right tab
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    // Click handling is externally controlled (disabled in prototype).
                    .clickable(enabled = enabled) { onTabSelected(NeedsReviewViewModel.Tab.NEEDS_REVIEW) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = needsReviewLabel,
                    fontWeight = if (selectedTab == NeedsReviewViewModel.Tab.NEEDS_REVIEW) FontWeight.W700 else FontWeight.W400,
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    color = if (selectedTab == NeedsReviewViewModel.Tab.NEEDS_REVIEW) selectedText else unselectedText
                )
            }
        }

        // Bottom indicator highlights the currently selected tab.
        Row(Modifier.fillMaxWidth()) {
            val leftIndicator = selectedTab == NeedsReviewViewModel.Tab.PRACTICE_HISTORY
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(indicatorHeight)
                    .background(if (leftIndicator) indicator else SurfaceWhite.copy(alpha = 0f))
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(indicatorHeight)
                    .background(if (!leftIndicator) indicator else SurfaceWhite.copy(alpha = 0f))
            )
        }
    }
}