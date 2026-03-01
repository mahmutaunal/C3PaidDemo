package com.mahmutalperenunal.c3paiddemo.features.practice.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

// ViewModel managing the state machine for Practice Pronunciation (Idle → Listening → Result).
class PracticePronunciationViewModel : ViewModel() {

    // Represents the distinct UI states rendered by the screen.
    sealed interface ScreenState {
        data object Idle : ScreenState
        data object Listening : ScreenState

        // Result state contains mock evaluation data shown after listening completes.
        data class Result(
            val score: Int = 90,
            val beforeText: String = "How r you?",
            val beforeScore: Int = 63,
            val afterText: String = "How are you?",
            val afterScore: Int = 90,
            val attemptLabel: String = "Attempt 1 • Keep going!",
            val nextStartsIn: Int = 3,
        ) : ScreenState
    }

    // Single source of truth for the screen UI.
    data class UiState(
        val title: String = "How are you?",
        val phonetic: String = "/hao a:r ju:/",
        val progress: Float = 0.78f,
        val state: ScreenState = ScreenState.Idle,
        val nextCountdownSec: Int = 3,
    )

    // Backing mutable state flow exposed as immutable StateFlow to UI.
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    // Jobs are tracked to prevent overlapping coroutines when state changes quickly.
    private var listeningJob: Job? = null
    private var resultJob: Job? = null

    fun showIdle() {
        // Ensure no background work is running when returning to Idle.
        listeningJob?.cancel()
        resultJob?.cancel()

        _uiState.update {
            it.copy(
                progress = 0.78f,
                state = ScreenState.Idle
            )
        }
    }

    fun startListening(listeningDurationSec: Int = 3) {
        listeningJob?.cancel()
        resultJob?.cancel()

        // Transition to Listening state and slightly adjust progress per prototype.
        _uiState.update {
            it.copy(
                progress = 0.7921f,
                state = ScreenState.Listening
            )
        }

        // Simulate microphone listening duration before showing result.
        listeningJob = viewModelScope.launch {
            delay(listeningDurationSec * 1000L)
            showResult()
        }
    }

    fun showResult(
        score: Int = 90,
        beforeText: String = "How r you?",
        beforeScore: Int = 63,
        afterText: String = "How are you?",
        afterScore: Int = 90,
        attemptLabel: String = "Attempt 1 • Keep going!",
        nextStartsIn: Int = 3,
        autoReturnSec: Int = 5,
    ) {
        listeningJob?.cancel()
        resultJob?.cancel()

        // Transition to Result state and populate evaluation data.
        _uiState.update {
            it.copy(
                progress = 1f,
                state = ScreenState.Result(
                    score = score,
                    beforeText = beforeText,
                    beforeScore = beforeScore,
                    afterText = afterText,
                    afterScore = afterScore,
                    attemptLabel = attemptLabel,
                    nextStartsIn = nextStartsIn,
                ),
                nextCountdownSec = nextStartsIn
            )
        }

        // Countdown displayed in UI before auto-returning to Idle.
        resultJob = viewModelScope.launch {
            val start = nextStartsIn.coerceAtLeast(1)
            for (sec in start downTo 1) {
                _uiState.update { it.copy(nextCountdownSec = sec) }
                delay(1000L)
            }
        }

        // Automatically reset screen back to Idle after a delay.
        viewModelScope.launch {
            delay(autoReturnSec * 1000L)
            showIdle()
        }
    }

    fun tryAgain() {
        showIdle()
    }
}