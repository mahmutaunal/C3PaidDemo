package com.mahmutalperenunal.c3paiddemo.features.review.data

import com.mahmutalperenunal.c3paiddemo.features.review.model.ReviewItemUi

// Temporary in-memory data used to simulate backend response for the prototype.
internal object ReviewFakeData {
    fun items(): List<ReviewItemUi> = listOf(
        ReviewItemUi(
            id = "1",
            title = "How are you?",
            subtitle = "¿Cómo estás?  •  /hao a:r ju:/",
            youSaid = "How r you?",
            correct = "How are you?",
            explanation = "Pronounce auxiliary verbs fully for clear communication. " +
                    "The word 'are' should be fully pronounced, not abbreviated as 'r'. " +
                    "In formal English, it's important to pronounce auxiliary verbs clearly."
        ),
        ReviewItemUi(
            id = "2",
            title = "Good morning",
            subtitle = "Buenos días  •  /gʊd 'mɔːrnɪŋ/",
            youSaid = "Good mornin",
            correct = "Good morning",
            explanation = "Pronounce the final consonant clearly. Avoid dropping ending sounds. " +
                    "This improves clarity and helps listeners understand you immediately."
        ),
    )
}