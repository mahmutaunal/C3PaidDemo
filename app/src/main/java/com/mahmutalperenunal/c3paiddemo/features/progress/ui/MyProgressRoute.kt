package com.mahmutalperenunal.c3paiddemo.features.progress.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.mahmutalperenunal.c3paiddemo.R

/**
 * Entry screen placeholder.
 * UI will be implemented to match the Figma design later.
 */
@Composable
fun MyProgressRoute(
    onOpenNeedsReview: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = onOpenNeedsReview) {
            Text(text = stringResource(id = R.string.my_progress_go_to_needs_review))
        }
    }
}