package com.mahmutalperenunal.c3paiddemo.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mahmutalperenunal.c3paiddemo.core.ui.theme.ReviewSoundBg
import com.mahmutalperenunal.c3paiddemo.core.ui.theme.SentCtaBg
import com.mahmutalperenunal.c3paiddemo.core.ui.theme.SentCtaIcon
import com.mahmutalperenunal.c3paiddemo.core.ui.theme.SentEnd
import com.mahmutalperenunal.c3paiddemo.core.ui.theme.TextSecondary

// Card displaying the current word/phrase with playback action (Idle/Listening states).
@Composable
fun PracticeWordCard(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    onSoundClick: () -> Unit,
) {
    // Colors are derived from theme tokens to stay consistent with the prototype.
    val bg = SentCtaBg
    val titleColor = SentCtaIcon
    val subtitleColor = TextSecondary
    val soundBg = ReviewSoundBg
    val soundIcon = SentEnd

    Surface(
        modifier = modifier
            .fillMaxWidth()
            // Fixed height to match Figma card proportions.
            .height(150.dp),
        color = bg,
        shape = RoundedCornerShape(12.dp),
        tonalElevation = 0.dp,
        shadowElevation = 1.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 12.dp, top = 12.dp, end = 16.dp, bottom = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .background(soundBg, CircleShape)
                    // Triggers text-to-speech playback (simulated in prototype).
                    .clickable {
                        onSoundClick()
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.VolumeUp,
                    // Decorative icon; button meaning is clear from context.
                    contentDescription = null,
                    tint = soundIcon,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(Modifier.width(12.dp))

            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                Text(
                    text = title,
                    fontWeight = FontWeight.W700,
                    fontSize = 24.sp,
                    lineHeight = 32.sp,
                    color = titleColor
                )
                Text(
                    text = subtitle,
                    fontWeight = FontWeight.W400,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    color = subtitleColor
                )
            }
        }
    }
}