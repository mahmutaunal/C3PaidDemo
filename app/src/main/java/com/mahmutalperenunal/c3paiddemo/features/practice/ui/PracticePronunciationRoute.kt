package com.mahmutalperenunal.c3paiddemo.features.practice.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.res.stringResource
import com.mahmutalperenunal.c3paiddemo.R
import com.mahmutalperenunal.c3paiddemo.core.ui.components.CenterMicSection
import com.mahmutalperenunal.c3paiddemo.core.ui.components.PracticeResultContent
import com.mahmutalperenunal.c3paiddemo.core.ui.components.PracticeTopProgress
import com.mahmutalperenunal.c3paiddemo.core.ui.components.PracticeTopProgressMode
import com.mahmutalperenunal.c3paiddemo.core.ui.components.PracticeTopProgressResult
import com.mahmutalperenunal.c3paiddemo.core.ui.components.PracticeWordCard
import com.mahmutalperenunal.c3paiddemo.core.ui.theme.MicListeningYellow
import com.mahmutalperenunal.c3paiddemo.core.ui.theme.PracticeGradientEnd
import com.mahmutalperenunal.c3paiddemo.core.ui.theme.PracticeGradientStart
import com.mahmutalperenunal.c3paiddemo.core.ui.theme.ScreenBg
import com.mahmutalperenunal.c3paiddemo.core.ui.theme.WordsCtaIcon

// Route composable hosting the Practice Pronunciation flow (Idle → Listening → Result).
@Composable
fun PracticePronunciationRoute(
    onBack: () -> Unit,
    vm: PracticePronunciationViewModel = viewModel(),
) {
    // Collect UI state from ViewModel (single source of truth).
    val ui by vm.uiState.collectAsState()

    // Top gradient matches the prototype header styling.
    val gradient = Brush.linearGradient(
        colors = listOf(PracticeGradientStart, PracticeGradientEnd)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            // Screen background below the gradient header.
            .background(ScreenBg)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                // Fixed header height based on Figma measurements.
                .height(116.06.dp)
                .background(gradient)
                .padding(bottom = 4.dp)
        ) {
            Spacer(Modifier.height(48.06.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .padding(horizontal = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBack, modifier = Modifier.size(48.dp)) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        // Decorative back icon; navigation meaning is clear from context.
                        contentDescription = null,
                        tint = Color.White
                    )
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(28.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.practice_pronunciation_title),
                        fontWeight = FontWeight.W700,
                        fontSize = 22.sp,
                        lineHeight = 28.sp,
                        color = Color.White
                    )
                }

                Spacer(Modifier.size(48.dp))
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 116.06.dp)
        ) {
            Spacer(Modifier.height(14.dp))

            // Top progress switches to result style when practice is completed.
            if (ui.state is PracticePronunciationViewModel.ScreenState.Result) {
                PracticeTopProgressResult(
                    modifier = Modifier.padding(start = 17.dp, end = 17.dp)
                )
            } else {
                val mode = when (ui.state) {
                    PracticePronunciationViewModel.ScreenState.Listening -> PracticeTopProgressMode.LISTENING
                    else -> PracticeTopProgressMode.IDLE
                }

                PracticeTopProgress(
                    modifier = Modifier.padding(start = 17.dp, end = 17.dp),
                    mode = mode
                )
            }

            Spacer(Modifier.height(17.dp))

            // Main content switches by screen state (Idle, Listening, Result).
            when (val st = ui.state) {
                PracticePronunciationViewModel.ScreenState.Idle -> {
                    PracticeWordCard(
                        modifier = Modifier.padding(start = 17.dp, end = 17.dp),
                        title = ui.title,
                        subtitle = ui.phonetic,
                        onSoundClick = { /* optional */ }
                    )

                    Spacer(Modifier.height(49.dp))

                    CenterMicSection(
                        label = stringResource(R.string.practice_pronunciation_tap_to_start_recording),
                        micColor = WordsCtaIcon,
                        shadow = 10.dp,
                        showPulse = false,
                        pulseColor = WordsCtaIcon.copy(alpha = 0.22f),
                        // Starts simulated listening phase (3s) handled by ViewModel.
                        onMicClick = { vm.startListening(listeningDurationSec = 3) }
                    )
                }

                PracticePronunciationViewModel.ScreenState.Listening -> {
                    PracticeWordCard(
                        modifier = Modifier.padding(start = 17.dp, end = 17.dp),
                        title = ui.title,
                        subtitle = ui.phonetic,
                        onSoundClick = { /* optional */ }
                    )

                    Spacer(Modifier.height(45.dp))

                    CenterMicSection(
                        label = stringResource(R.string.practice_pronunciation_listening),
                        micColor = MicListeningYellow,
                        shadow = 0.dp,
                        showPulse = true,
                        pulseColor = MicListeningYellow.copy(alpha = 0.22f),
                        // Disabled during active listening to prevent duplicate triggers.
                        onMicClick = { /* no-op */ }
                    )
                }

                is PracticePronunciationViewModel.ScreenState.Result -> {
                    PracticeResultContent(
                        modifier = Modifier.fillMaxSize(),
                        score = st.score,
                        beforeText = st.beforeText,
                        beforeScore = st.beforeScore,
                        afterText = st.afterText,
                        afterScore = st.afterScore,
                        attemptLabel = st.attemptLabel,
                        nextStartsIn = ui.nextCountdownSec,
                        // Resets state back to Idle for another attempt.
                        onTryAgain = { vm.tryAgain() }
                    )
                }
            }
        }
    }
}