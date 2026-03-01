package com.mahmutalperenunal.c3paiddemo.features.review.model

// UI model representing a single review item displayed on the Needs Review screen.
data class ReviewItemUi(
    val id: String,
    val title: String,
    val subtitle: String,
    val youSaid: String,
    val correct: String,
    val explanation: String,
    val isDone: Boolean = false,
)