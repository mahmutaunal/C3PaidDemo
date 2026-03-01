package com.mahmutalperenunal.c3paiddemo.core.ui.components

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mahmutalperenunal.c3paiddemo.core.ui.theme.PracticeMicLabelColor
import com.mahmutalperenunal.c3paiddemo.core.ui.theme.SurfaceWhite

// Reusable centered microphone section with optional pulse animation (Idle/Listening states).
@Composable
fun CenterMicSection(
    label: String,
    micColor: Color,
    shadow: Dp,
    showPulse: Boolean,
    pulseColor: Color,
    onMicClick: () -> Unit
) {
    // Infinite transition drives the pulse animation when listening is active.
    val infinite = rememberInfiniteTransition(label = "mic_pulse")

    // Expanding scale effect for the outer pulse circle.
    val pulseScale by infinite.animateFloat(
        initialValue = 1.0f,
        targetValue = 1.18f,
        animationSpec = infiniteRepeatable(
            animation = tween(750),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse_scale"
    )

    // Fading alpha effect synced with the scale animation.
    val pulseAlpha by infinite.animateFloat(
        initialValue = 0.55f,
        targetValue = 0.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(750),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse_alpha"
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 17.dp, end = 17.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = label,
            fontWeight = FontWeight.W500,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            color = PracticeMicLabelColor
        )

        Spacer(Modifier.height(12.dp))

        Box(
            modifier = Modifier.size(96.dp),
            contentAlignment = Alignment.Center
        ) {
            if (showPulse) {
                // Animated outer circle indicating active listening state.
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .scale(pulseScale)
                        .background(pulseColor.copy(alpha = pulseAlpha), CircleShape)
                )
            }

            // Main microphone button surface (clickable).
            Surface(
                onClick = onMicClick,
                shape = CircleShape,
                color = micColor,
                tonalElevation = 0.dp,
                shadowElevation = shadow,
                modifier = Modifier.matchParentSize()
            ) {
                Box(contentAlignment = Alignment.Center) {
                    // Decorative icon; action is implied by mic shape and label.
                    Icon(
                        imageVector = Icons.Filled.Mic,
                        contentDescription = null,
                        tint = SurfaceWhite,
                        modifier = Modifier.size(57.6.dp)
                    )
                }
            }
        }
    }
}