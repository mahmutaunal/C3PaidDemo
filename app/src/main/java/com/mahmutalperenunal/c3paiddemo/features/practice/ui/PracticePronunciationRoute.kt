package com.mahmutalperenunal.c3paiddemo.features.practice.ui

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
 * Practice pronunciation screen placeholder.
 * Will include recording + result states as shown in the prototype.
 */
@Composable
fun PracticePronunciationRoute(
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(id = R.string.practice_pronunciation_placeholder))
        Button(onClick = onBack) {
            Text(text = stringResource(id = R.string.common_back))
        }
    }
}