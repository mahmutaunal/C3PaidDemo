package com.mahmutalperenunal.c3paiddemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import com.mahmutalperenunal.c3paiddemo.core.ui.theme.C3PaidDemoTheme
import com.mahmutalperenunal.c3paiddemo.navigation.AppNavGraph

// Single-activity entry point hosting the Compose UI + Navigation graph.
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // App-wide theme wrapper (colors/typography) aligned with the prototype.
            C3PaidDemoTheme {
                // Surface provides the default background and Material styling for the whole app.
                Surface {
                    // Navigation controller is remembered across recompositions.
                    val navController = rememberNavController()
                    AppNavGraph(navController = navController)
                }
            }
        }
    }
}