package com.mahmutalperenunal.c3paiddemo.features.review.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mahmutalperenunal.c3paiddemo.R

/**
 * Needs Review screen placeholder.
 * Will be replaced with the tab layout + review cards from the prototype.
 */
@Composable
fun NeedsReviewRoute(
    onBack: () -> Unit,
    onOpenPractice: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = onBack) {
            Text(text = stringResource(id = R.string.needs_review_back))
        }

        Button(onClick = onOpenPractice) {
            Text(text = stringResource(id = R.string.needs_review_open_practice))
        }
    }
}