package com.mahmutalperenunal.c3paiddemo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mahmutalperenunal.c3paiddemo.features.practice.ui.PracticePronunciationRoute
import com.mahmutalperenunal.c3paiddemo.features.progress.ui.MyProgressRoute
import com.mahmutalperenunal.c3paiddemo.features.review.ui.NeedsReviewRoute


// Centralized navigation graph defining all prototype-accessible destinations.
@Composable
fun AppNavGraph(
    navController: NavHostController,
    // Default start matches the prototype entry screen.
    startDestination: String = Routes.MY_PROGRESS
) {
    // NavHost ties the NavController to composable destinations.
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        // Progress screen (prototype landing screen).
        composable(Routes.MY_PROGRESS) {
            MyProgressRoute(
                onOpenNeedsReview = { navController.navigate(Routes.NEEDS_REVIEW) }
            )
        }

        // Needs Review screen opened from Progress card.
        composable(Routes.NEEDS_REVIEW) {
            NeedsReviewRoute(
                onBack = { navController.popBackStack() },
                onOpenPractice = { navController.navigate(Routes.PRACTICE_PRONUNCIATION) }
            )
        }

        // Practice Pronunciation screen opened from Needs Review.
        composable(Routes.PRACTICE_PRONUNCIATION) {
            PracticePronunciationRoute(
                onBack = { navController.popBackStack() }
            )
        }
    }
}