package com.mahmutalperenunal.c3paiddemo.features.review.ui

import androidx.lifecycle.ViewModel
import com.mahmutalperenunal.c3paiddemo.features.review.data.ReviewFakeData
import com.mahmutalperenunal.c3paiddemo.features.review.model.ReviewItemUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

// ViewModel managing state for Needs Review screen (translate + mark done logic).
class NeedsReviewViewModel : ViewModel() {

    // Single source of truth for the Needs Review UI.
    data class UiState(
        val selectedTab: Tab = Tab.NEEDS_REVIEW,
        val items: List<ReviewItemUi> = ReviewFakeData.items(),
        val translatedIds: Set<String> = emptySet(),
        val doneIds: Set<String> = emptySet(),
    )

    // Tabs are present for UI parity but switching is disabled per prototype requirement.
    enum class Tab { PRACTICE_HISTORY, NEEDS_REVIEW }

    // Backing mutable state exposed as immutable StateFlow to the UI layer.
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    fun toggleTranslated(id: String) {
        // Toggles per-item translated flag (stored as a set of item IDs).
        _uiState.update { s ->
            val newSet = if (id in s.translatedIds) s.translatedIds - id else s.translatedIds + id
            s.copy(translatedIds = newSet)
        }
    }

    fun markDone(id: String) {
        // Marks item as done; UI filters these out from the visible list.
        _uiState.update { s -> s.copy(doneIds = s.doneIds + id) }
    }
}