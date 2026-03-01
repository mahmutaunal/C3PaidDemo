package com.mahmutalperenunal.c3paiddemo.features.review.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mahmutalperenunal.c3paiddemo.R
import com.mahmutalperenunal.c3paiddemo.core.ui.components.NeedsReviewTabs
import com.mahmutalperenunal.c3paiddemo.core.ui.components.ReviewItemCard
import com.mahmutalperenunal.c3paiddemo.core.ui.theme.PurplePrimary
import com.mahmutalperenunal.c3paiddemo.core.ui.theme.ScreenBg
import com.mahmutalperenunal.c3paiddemo.core.ui.theme.SurfaceWhite

// Needs Review screen showing review cards with translate + practice actions (per prototype).
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NeedsReviewRoute(
    onBack: () -> Unit,
    onOpenPractice: () -> Unit,
    vm: NeedsReviewViewModel = viewModel(),
) {
    // Collect UI state from ViewModel (single source of truth).
    val state by vm.uiState.collectAsState()

    // Scaffold with top app bar + tab strip + list content.
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.speaking_practice_title)) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            // Decorative back icon; navigation meaning is clear from context.
                            contentDescription = null
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = PurplePrimary,
                    titleContentColor = SurfaceWhite,
                    navigationIconContentColor = SurfaceWhite
                )
            )
        },
        containerColor = ScreenBg
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(ScreenBg)
                .padding(padding)
        ) {
            NeedsReviewTabs(
                selectedTab = state.selectedTab,
                // Tabs are visible but disabled in the prototype (requirements).
                onTabSelected = { /* disabled by requirement */ },
                enabled = false,
                practiceHistoryLabel = stringResource(R.string.practice_history),
                needsReviewLabel = stringResource(R.string.needs_review),
            )

            // Hide items marked as done so the list matches the expected UX.
            val visibleItems = remember(state.items, state.doneIds) {
                state.items.filterNot { it.id in state.doneIds }
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    start = 17.dp,
                    end = 17.dp,
                    top = 16.dp,
                    bottom = 16.dp
                ),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                items(visibleItems, key = { it.id }) { item ->
                    // Per-item UI flags stored in ViewModel state.
                    val isTranslated = item.id in state.translatedIds
                    val isDone = item.id in state.doneIds

                    // Subtitle packs multiple bits of info; split into translated + phonetic parts.
                    val parts = item.subtitle.split("•").map { it.trim() }
                    val translatedText = parts.getOrNull(0).orEmpty()
                    val phoneticText = parts.getOrNull(1).orEmpty()

                    ReviewItemCard(
                        title = item.title,
                        translated = if (isTranslated) {
                            stringResource(R.string.translated_label)
                        } else translatedText,
                        phonetic = phoneticText,
                        youSaid = item.youSaid,
                        correct = item.correct,
                        explanation = if (isTranslated) {
                            stringResource(R.string.translated_label)
                        } else item.explanation,
                        isDone = isDone,
                        // Translate toggles between raw subtitle text and localized "Translated" label.
                        onToggleTranslate = { vm.toggleTranslated(item.id) },
                        // Navigates to Practice Pronunciation flow.
                        onPracticeAgain = onOpenPractice,
                        // Marks item as done; it disappears from the list.
                        onMarkDone = { vm.markDone(item.id) },
                    )
                }
            }
        }
    }
}