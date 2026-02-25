package com.mahmutalperenunal.c3paiddemo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mahmutalperenunal.c3paiddemo.features.practice.ui.PracticePronunciationRoute
import com.mahmutalperenunal.c3paiddemo.features.progress.ui.MyProgressRoute
import com.mahmutalperenunal.c3paiddemo.features.review.ui.NeedsReviewRoute

/**
 * App navigation graph.
 * Each destination is represented by a "Route" composable wrapper.
 */
@Composable
fun AppNavGraph(
    navController: NavHostController,
    startDestination: String = Routes.MY_PROGRESS
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Routes.MY_PROGRESS) {
            MyProgressRoute(
                onOpenNeedsReview = { navController.navigate(Routes.NEEDS_REVIEW) }
            )
        }

        composable(Routes.NEEDS_REVIEW) {
            NeedsReviewRoute(
                onBack = { navController.popBackStack() },
                onOpenPractice = { navController.navigate(Routes.PRACTICE_PRONUNCIATION) }
            )
        }

        composable(Routes.PRACTICE_PRONUNCIATION) {
            PracticePronunciationRoute(
                onBack = { navController.popBackStack() }
            )
        }
    }
}